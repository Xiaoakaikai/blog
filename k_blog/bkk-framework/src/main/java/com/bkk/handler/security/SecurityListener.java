package com.bkk.handler.security;

import com.bkk.domain.entity.User;
import com.bkk.domain.vo.OnlineUserVo;
import com.bkk.mapper.UserMapper;
import com.bkk.utils.IpUtils;
import com.bkk.utils.JwtUtil;
import eu.bitwalker.useragentutils.UserAgent;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
@RequiredArgsConstructor
public class SecurityListener {

    public static final List<OnlineUserVo> ONLINE_USERS = new CopyOnWriteArrayList<>();

    private final HttpServletRequest request;

    private final UserMapper userMapper;


    /**
     * 登录成功后执行的操作
     * @param userId
     * @param token
     */
    @Transactional(rollbackFor = Exception.class)
    public void loginSuccess(Long userId, String token) {
        //更新登录用户的一些信息
        String ipAddress = IpUtils.getIpAddress(request);
        String ipSource = IpUtils.getIpSource(ipAddress);
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("user-agent"));
        String browser = userAgent.getBrowser().toString();
        String os = userAgent.getOperatingSystem().getName();
        userMapper.updateLoginInfo(userId, ipAddress, ipSource,
                os, browser);

        Claims claims = null;
        try {
            claims = JwtUtil.parseJWT(token);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Date expiration = claims.getExpiration();

        User user = userMapper.selectById(userId);
        ONLINE_USERS.add(OnlineUserVo.builder().userId(user.getId())
                .nickname(user.getNickname())
                .avatar(user.getAvatar())
                .ip(ipAddress)
                .os(os)
                .city(ipSource)
                .browser(browser)
                .loginTime(new Date())
                .tokenValue(token)
                .expireTime(expiration.getTime())
                .build());
    }


    /**
     * 注销时执行的操作
     */
    public void logoutSuccess() {
        String token = request.getHeader("token");
        long now = System.currentTimeMillis();
        ONLINE_USERS.removeIf(onlineUser ->
                onlineUser.getTokenValue().equals(token)
        );
    }

    /**
     * 踢出用户后执行的操作
     */
    public void kickOutEvent(String token) {
        ONLINE_USERS.removeIf(onlineUser ->
                onlineUser.getTokenValue().equals(token)
        );
    }

    /**
     * 清理过期的 Token
     */
    @Scheduled(cron = "0 */1 * * * ?")
    public void cleanupExpiredTokens() {
        long now = System.currentTimeMillis();
        List<OnlineUserVo> expiredUsers = new ArrayList<>();
        for (OnlineUserVo user : ONLINE_USERS) {
            if (user.getExpireTime() < now) {
                expiredUsers.add(user);
            }
        }
        ONLINE_USERS.removeAll(expiredUsers);
    }
}

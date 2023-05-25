package com.bkk.service.impl;

import com.bkk.domain.ResponseResult;
import com.bkk.domain.dto.LoginDTO;
import com.bkk.domain.entity.LoginUser;
import com.bkk.exception.SystemException;
import com.bkk.handler.security.SecurityListener;
import com.bkk.mapper.UserMapper;
import com.bkk.service.LoginService;
import com.bkk.utils.BaseContext;
import com.bkk.utils.IpUtils;
import com.bkk.utils.JwtUtil;
import com.bkk.utils.RedisCache;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private SecurityListener securityListener;

    /**
     * 后台登录
     * @param dto
     * @return
     */
    @Override
    public ResponseResult login(LoginDTO dto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dto.getUsername(),dto.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //判断是否认证通过
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或密码错误");
        }
        //获取userid 生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        Long roleId = loginUser.getUser().getRoleId();
        if (roleId != 1 && roleId != 5) {
            throw new SystemException("请使用演示用户登录！！");
        }
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        //把用户信息存入redis
        redisCache.setCacheObject("adminlogin:"+userId, loginUser);

        //登录成功后的一些方法
        securityListener.loginSuccess(Long.parseLong(userId), jwt);

        //把token封装 返回
        return ResponseResult.okResult(jwt);
    }

    /**
     * 退出登录
     * @return
     */
    @Override
    public ResponseResult logout() {
        //获取userid
        Long userId = BaseContext.getCurrentId();
        //删除redis中的用户信息
        redisCache.deleteObject("adminlogin:" + userId);

        //注销后执行的一些操作
        securityListener.logoutSuccess();
        return ResponseResult.okResult();
    }
}

package com.bkk.service.impl;

import com.bkk.domain.ResponseResult;
import com.bkk.domain.dto.EmailLoginDTO;
import com.bkk.domain.dto.LoginDTO;
import com.bkk.domain.entity.LoginUser;
import com.bkk.domain.vo.UserInfoVO;
import com.bkk.enums.AppHttpCodeEnum;
import com.bkk.handler.security.SecurityListener;
import com.bkk.service.BlogLoginService;
import com.bkk.utils.BaseContext;
import com.bkk.utils.BeanCopyUtils;
import com.bkk.utils.JwtUtil;
import com.bkk.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Objects;
import java.util.regex.Pattern;

import static com.bkk.enums.AppHttpCodeEnum.EMAIL_ERROR;

@Service
public class BlogLoginServiceImpl implements BlogLoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private SecurityListener securityListener;

    /**
     * 用户登录
     * @param emailLoginDTO
     * @return
     */
    @Override
    public ResponseResult login(EmailLoginDTO emailLoginDTO) {
        checkEmail(emailLoginDTO.getEmail());
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(emailLoginDTO.getEmail(),emailLoginDTO.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //判断是否认证通过
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或密码错误");
        }
        //获取userid 生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        //把用户信息存入redis
        redisCache.setCacheObject("bloglogin:"+userId,loginUser);

        //登录成功后的一些方法
        securityListener.loginSuccess(Long.parseLong(userId), jwt);

        //把token和userinfo封装 返回

        //把User转换成UserInfoVo
        UserInfoVO userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVO.class);
        userInfoVo.setToken(jwt);
        return ResponseResult.okResult(userInfoVo);
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
        redisCache.deleteObject("bloglogin:" + userId);

        //注销后执行的一些操作
        securityListener.logoutSuccess();
        return ResponseResult.okResult();
    }

    public void checkEmail(String email){
        boolean matches = Pattern.compile("\\w+@{1}\\w+\\.{1}\\w+").matcher(email).matches();
        Assert.isTrue(matches, EMAIL_ERROR.getMsg());
    }
}

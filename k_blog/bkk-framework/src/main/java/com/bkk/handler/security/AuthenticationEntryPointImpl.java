package com.bkk.handler.security;

import com.alibaba.fastjson.JSON;
import com.bkk.domain.ResponseResult;
import com.bkk.enums.AppHttpCodeEnum;
import com.bkk.utils.WebUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        authException.printStackTrace();
        //InsufficientAuthenticationException
        //BadCredentialsException
        ResponseResult result = null;
        if(authException instanceof BadCredentialsException){
            result = ResponseResult.errorResult(AppHttpCodeEnum.ERROR_PASSWORD.getCode(),authException.getMessage());
        }else if(authException instanceof InsufficientAuthenticationException){
            result = ResponseResult.errorResult(AppHttpCodeEnum.NOT_LOGIN);
        }else{
            result = ResponseResult.errorResult(AppHttpCodeEnum.ERROR_DEFAULT.getCode(),"认证或授权失败");
        }
        //响应给前端
        WebUtils.renderString(response, JSON.toJSONString(result));
    }
}

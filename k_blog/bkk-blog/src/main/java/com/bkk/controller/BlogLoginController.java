package com.bkk.controller;

import com.bkk.domain.ResponseResult;
import com.bkk.domain.dto.EmailLoginDTO;
import com.bkk.domain.dto.LoginDTO;
import com.bkk.enums.AppHttpCodeEnum;
import com.bkk.exception.SystemException;
import com.bkk.service.BlogLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.bkk.enums.AppHttpCodeEnum.PARAMS_ILLEGAL;

@RestController
@Api(tags = "前台登录")
public class BlogLoginController {

    @Autowired
    private BlogLoginService blogLoginService;

    @PostMapping("/login")
    @ApiOperation(value = "用户登录", httpMethod = "POST", response = ResponseResult.class, notes = "用户登录")
    public ResponseResult login(@RequestBody EmailLoginDTO emailLoginDTO){
        if (!StringUtils.hasText(emailLoginDTO.getEmail())) {
            throw new SystemException(AppHttpCodeEnum.PARAMS_ILLEGAL);
        }
        return blogLoginService.login(emailLoginDTO);
    }

    @PostMapping("/logout")
    @ApiOperation(value = "用户退出", httpMethod = "POST", response = ResponseResult.class, notes = "用户退出")
    public ResponseResult logout(){
        return blogLoginService.logout();
    }
}

package com.bkk.controller;

import com.bkk.domain.ResponseResult;
import com.bkk.domain.dto.LoginDTO;
import com.bkk.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "登录")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    @ApiOperation(value = "用户登录", httpMethod = "POST", response = ResponseResult.class, notes = "用户登录")
    public ResponseResult<String> login(@RequestBody LoginDTO dto) {
        return loginService.login(dto);
    }

    @GetMapping("/logout")
    @ApiOperation(value = "用户退出", httpMethod = "GET", response = ResponseResult.class, notes = "用户退出")
    public ResponseResult logout() {
        return loginService.logout();
    }

}

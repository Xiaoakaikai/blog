package com.bkk.controller;

import com.bkk.annotation.BusinessLogger;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.dto.EmailRegisterDTO;
import com.bkk.domain.dto.UserDTO;
import com.bkk.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ResourceBundle;

@RestController
@RequestMapping("/user")
@Api(tags = "前台用户管理")
public class UserController {

    @Autowired
    private UserService userService;

    @BusinessLogger(value = "个人中心-修改用户信息",type = "修改",desc = "修改用户信息")
    @PutMapping("/updateUser")
    @ApiOperation(value = "修改信息", httpMethod = "PUT", response = ResponseResult.class, notes = "修改信息")
    public ResponseResult updateUser(@Valid @RequestBody UserDTO userDTO) {
        return userService.updateUser(userDTO);
    }

    @PostMapping("/emailRegister")
    @ApiOperation(value = "邮箱注册", httpMethod = "POST", response = ResponseResult.class, notes = "邮箱注册")
    public ResponseResult emailRegister(@Valid @RequestBody EmailRegisterDTO dto) {
        return userService.emailRegister(dto);
    }

    @GetMapping("/sendEmailCode")
    @ApiOperation(value = "发送邮箱验证码", httpMethod = "GET", response = ResponseResult.class, notes = "发送邮箱验证码")
    public ResponseResult sendEmailCode(@RequestParam String email) {
        return userService.sendEmailCode(email);
    }

    @BusinessLogger(value = "个人中心-绑定邮箱",type = "修改",desc = "绑定邮箱")
    @PostMapping("/bindEmail")
    @ApiOperation(value = "绑定邮箱", httpMethod = "POST", response = ResponseResult.class, notes = "绑定邮箱")
    public ResponseResult bindEmail(@Valid @RequestBody UserDTO userDTO) {
        return userService.bindEmail(userDTO);
    }

    @PostMapping("/updatePassword")
    public ResponseResult blogUpdatePassword(@Valid @RequestBody EmailRegisterDTO dto) {
        return userService.blogUpdatePassword(dto);
    }
}

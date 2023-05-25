package com.bkk.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bkk.annotation.OperationLogger;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.entity.Menu;
import com.bkk.domain.vo.SystemUserVO;
import com.bkk.domain.vo.UserVO;
import com.bkk.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Api(tags = "用户管理")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/getCurrentUserInfo")
    @ApiOperation(value = "当前用户详情", httpMethod = "POST", response = ResponseResult.class, notes = "当前用户详情")
    public ResponseResult<SystemUserVO> getCurrentUserInfo() {
        return userService.getCurrentUserInfo();
    }

    @PostMapping("/getUserMenu")
    @ApiOperation(value = "用户菜单树", httpMethod = "POST", response = ResponseResult.class, notes = "用户菜单树")
    public ResponseResult<List<Menu>> getUserMenu() {
        return userService.getUserMenu();
    }

    @GetMapping("/list")
    @ApiOperation(value = "用户列表", httpMethod = "GET", response = ResponseResult.class, notes = "用户列表")
    public ResponseResult<Page<UserVO>> list(Integer pageNo, Integer pageSize, String username, Integer loginType) {
        return userService.list(pageNo, pageSize, username, loginType);
    }

    @GetMapping("/info")
    @ApiOperation(value = "用户详情", httpMethod = "GET", response = ResponseResult.class, notes = "用户详情")
    public ResponseResult<SystemUserVO> info(@RequestParam Long id) {
        return userService.info(id);
    }

    @OperationLogger(value = "修改用户")
    @PutMapping("/update")
    @ApiOperation(value = "修改用户", httpMethod = "PUT", response = ResponseResult.class, notes = "修改用户")
    public ResponseResult update(@RequestBody SystemUserVO user) {
        return userService.update(user);
    }

    @OperationLogger(value = "删除用户")
    @DeleteMapping("/remove")
    @ApiOperation(value = "删除用户", httpMethod = "DELETE", response = ResponseResult.class, notes = "删除用户")
    public ResponseResult remove(@RequestBody List<Long> ids) {
        return userService.remove(ids);
    }

    @GetMapping("/online")
    @ApiOperation(value = "在线用户列表", httpMethod = "GET", response = ResponseResult.class, notes = "在线用户列表")
    public ResponseResult online(Integer pageNo, Integer pageSize, String name) {
        return userService.online(pageNo, pageSize, name);
    }

    @OperationLogger(value = "踢出用户")
    @GetMapping("/kick")
    @ApiOperation(value = "踢出用户", httpMethod = "GET", response = ResponseResult.class, notes = "踢出用户")
    public ResponseResult kick(String token) {
        return userService.kick(token);
    }

    @OperationLogger(value = "修改密码")
    @PostMapping("/updatePassword")
    @ApiOperation(value = "修改密码", httpMethod = "POST", response = ResponseResult.class, notes = "修改密码")
    public ResponseResult updatePassword(@RequestBody Map<String, String> map) {
        return userService.updatePassword(map);
    }
 }

package com.bkk.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bkk.annotation.OperationLogger;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.entity.UserLog;
import com.bkk.service.UserLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userLog")
@Api(tags = "用户日志管理")
public class UserLogController {

    @Autowired
    private UserLogService userLogService;

    @GetMapping("/list")
    @ApiOperation(value = "用户日志列表", httpMethod = "GET", response = ResponseResult.class, notes = "用户日志列表")
    public ResponseResult<Page<UserLog>> list(Integer pageNo, Integer pageSize) {
        return userLogService.list(pageNo, pageSize);
    }

    @OperationLogger(value = "删除用户日志")
    @DeleteMapping("/delete")
    @ApiOperation(value = "删除用户日志", httpMethod = "DELETE", response = ResponseResult.class, notes = "删除用户日志")
    public ResponseResult delete(@RequestBody List<Long> ids) {
        return userLogService.delete(ids);
    }
}

package com.bkk.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bkk.annotation.OperationLogger;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.entity.ExceptionLog;
import com.bkk.service.ExceptionLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/exceptionLog")
@Api(tags = "异常日志管理")
public class ExceptionLogController {

    @Autowired
    private ExceptionLogService exceptionLogService;

    @GetMapping("/list")
    @ApiOperation(value = "异常日志列表", httpMethod = "GET", response = ResponseResult.class, notes = "异常日志列表")
    public ResponseResult<Page<ExceptionLog>> list(Integer pageNo, Integer pageSize) {
        return exceptionLogService.list(pageNo, pageSize);
    }

    @OperationLogger(value = "删除异常日志")
    @DeleteMapping("/delete")
    @ApiOperation(value = "删除异常日志", httpMethod = "DELETE", response = ResponseResult.class, notes = "删除异常日志")
    public ResponseResult delete(@RequestBody List<Long> ids) {
        return exceptionLogService.delete(ids);
    }
}

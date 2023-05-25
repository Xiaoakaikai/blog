package com.bkk.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bkk.annotation.OperationLogger;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.entity.AdminLog;
import com.bkk.service.AdminLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adminLog")
@Api(tags = "操作日志管理")
public class AdminLogController {

    @Autowired
    private AdminLogService adminLogService;

    @GetMapping("/list")
    @ApiOperation(value = "操作日志列表", httpMethod = "GET", response = ResponseResult.class, notes = "操作日志列表")
    public ResponseResult<Page<AdminLog>> list(Integer pageNo, Integer pageSize) {
        return adminLogService.list(pageNo, pageSize);
    }

    @OperationLogger(value = "删除操作日志")
    @DeleteMapping("/delete")
    @ApiOperation(value = "删除操作日志", httpMethod = "DELETE", response = ResponseResult.class, notes = "删除操作日志")
    public ResponseResult delete(@RequestBody List<Long> ids) {
        return adminLogService.delete(ids);
    }
}

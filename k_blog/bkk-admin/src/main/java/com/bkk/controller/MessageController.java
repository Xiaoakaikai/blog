package com.bkk.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bkk.annotation.OperationLogger;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.entity.Message;
import com.bkk.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
@Api(tags = "留言管理")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/list")
    @ApiOperation(value = "留言列表", httpMethod = "GET", response = ResponseResult.class, notes = "留言列表")
    public ResponseResult<Page<Message>> list(Integer pageNo, Integer pageSize, String name) {
        return messageService.list(pageNo, pageSize, name);
    }

    @OperationLogger(value = "通过留言")
    @PostMapping("/passBatch")
    @ApiOperation(value = "通过留言", httpMethod = "POST", response = ResponseResult.class, notes = "通过留言")
    public ResponseResult passBatch(@RequestBody List<Long> ids) {
        return messageService.passBatch(ids);
    }

    @OperationLogger(value = "删除留言")
    @DeleteMapping("/remove")
    @ApiOperation(value = "删除留言", httpMethod = "DELETE", response = ResponseResult.class, notes = "删除留言")
    public ResponseResult remove(@RequestBody List<Long> ids) {
        return messageService.remove(ids);
    }

}

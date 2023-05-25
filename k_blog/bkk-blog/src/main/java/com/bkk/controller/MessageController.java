package com.bkk.controller;

import com.bkk.annotation.BusinessLogger;
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
@Api(tags = "前台留言管理")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @BusinessLogger(value = "留言板-留言列表",type = "查询",desc = "留言列表")
    @GetMapping("/webMessage")
    @ApiOperation(value = "留言列表", httpMethod = "GET", response = ResponseResult.class, notes = "留言列表")
    public ResponseResult<List<Message>> webMessage() {
        return messageService.webMessage();
    }

    @BusinessLogger(value = "留言板-用户留言",type = "查询",desc = "用户留言")
    @PostMapping("/add")
    @ApiOperation(value = "发送留言", httpMethod = "POST", response = ResponseResult.class, notes = "发送留言")
    public ResponseResult add(@RequestBody Message message) {
        return messageService.add(message);
    }
}

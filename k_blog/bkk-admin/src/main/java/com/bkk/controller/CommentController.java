package com.bkk.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bkk.annotation.OperationLogger;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.vo.SystemCommentVO;
import com.bkk.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@Api(tags = "评论管理")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/list")
    @ApiOperation(value = "评论列表", httpMethod = "GET", response = ResponseResult.class, notes = "评论列表")
    public ResponseResult<Page<SystemCommentVO>> list(Integer pageNo, Integer pageSize, String keywords) {
        return commentService.list(pageNo, pageSize, keywords);
    }

    @OperationLogger(value = "删除评论")
    @DeleteMapping("/remove")
    @ApiOperation(value = "删除评论", httpMethod = "DELETE", response = ResponseResult.class, notes = "删除评论")
    public ResponseResult remove(@RequestBody List<Long> ids) {
        return commentService.remove(ids);
    }
}

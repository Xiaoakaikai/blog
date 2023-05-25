package com.bkk.controller;

import com.bkk.annotation.BusinessLogger;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.dto.CommentDTO;
import com.bkk.domain.vo.ReplyVO;
import com.bkk.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comment")
@Api(tags = "前台评论管理")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @BusinessLogger(value = "评论模块-用户评论",type = "添加",desc = "用户评论")
    @PostMapping("/addComment")
    @ApiOperation(value = "用户评论", httpMethod = "POST", response = ResponseResult.class, notes = "用户评论")
    public ResponseResult addComment(@RequestBody CommentDTO commentDTO) {
        return commentService.addComment(commentDTO);
    }

    @BusinessLogger(value = "评论模块-查询评论",type = "查询",desc = "查询文章所有评论")
    @GetMapping("/comments")
    @ApiOperation(value = "文章评论列表", httpMethod = "GET", response = ResponseResult.class, notes = "文章评论列表")
    public ResponseResult<Map<String, Object>> comments(Integer pageNo, Integer pageSize, Long articleId) {
        return commentService.comments(pageNo, pageSize, articleId);
    }

    @BusinessLogger(value = "评论模块-查询回复评论",type = "查询",desc = "查询所有回复评论")
    @GetMapping("/repliesByComId")
    @ApiOperation(value = "回复评论列表", httpMethod = "GET", response = ResponseResult.class, notes = "回复评论列表")
    public ResponseResult<List<ReplyVO>> repliesByComId(Integer pageNo, Integer pageSize, Long commentId) {
        return commentService.repliesByComId(pageNo, pageSize, commentId);
    }
}

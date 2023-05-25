package com.bkk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.dto.CommentDTO;
import com.bkk.domain.entity.Comment;

import java.util.List;


/**
 * 评论表(Comment)表服务接口
 *
 * @author makejava
 * @since 2023-03-30 18:09:52
 */
public interface CommentService extends IService<Comment> {

    ResponseResult comments(Integer pageNo, Integer pageSize, Long articleId);

    ResponseResult repliesByComId(Integer pageNo, Integer pageSize, Long commentId);

    ResponseResult list(Integer pageNo, Integer pageSize, String keywords);

    ResponseResult remove(List<Long> ids);

    ResponseResult addComment(CommentDTO commentDTO);
}


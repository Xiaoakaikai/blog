package com.bkk.mapper;
import com.bkk.domain.entity.Comment;
import com.bkk.domain.vo.ReplyCountVO;
import com.bkk.domain.vo.ReplyVO;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;


/**
 * 评论表(Comment)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-30 18:09:51
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

    List<ReplyVO> listReplies(Long id);

    ReplyCountVO listReplyCountByCommentId(Long id);

}

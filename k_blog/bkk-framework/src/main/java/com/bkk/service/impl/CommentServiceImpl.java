package com.bkk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.dto.CommentDTO;
import com.bkk.domain.entity.Article;
import com.bkk.domain.entity.Comment;
import com.bkk.domain.entity.User;
import com.bkk.domain.vo.CommentVO;
import com.bkk.domain.vo.ReplyCountVO;
import com.bkk.domain.vo.ReplyVO;
import com.bkk.domain.vo.SystemCommentVO;
import com.bkk.exception.SystemException;
import com.bkk.mapper.CommentMapper;
import com.bkk.service.ArticleService;
import com.bkk.service.CommentService;
import com.bkk.service.UserService;
import com.bkk.utils.BaseContext;
import com.bkk.utils.BeanCopyUtils;
import com.bkk.utils.DateUtils;
import com.bkk.utils.HTMLUtils;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.bkk.constants.SystemConstants.REPLY_COUNT;

/**
 * 评论表(Comment)表服务实现类
 *
 * @author makejava
 * @since 2023-03-30 18:09:52
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    /**
     * 获取文章的评论
     * @param pageNo
     * @param pageSize
     * @param articleId
     * @return
     */
    @Override
    public ResponseResult comments(Integer pageNo, Integer pageSize, Long articleId) {
        // 查询文章评论量
        Integer commentCount = count(new LambdaQueryWrapper<Comment>()
                .eq(Objects.nonNull(articleId), Comment::getArticleId, articleId)
                .isNull(Objects.isNull(articleId), Comment::getArticleId)
                .isNull(Comment::getParentId));
        if (commentCount == 0) {
            return ResponseResult.okResult();
        }
        //添加条件
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getArticleId, articleId).isNull(Comment::getParentId).orderByDesc(Comment::getId);
        Page<Comment> page = new Page<>(pageNo, pageSize);
        page(page, queryWrapper);

        List<Comment> commentList = page.getRecords();
        List<CommentVO> commentVOList = commentList.stream()
                .map(new Function<Comment, CommentVO>() {
                    @Override
                    public CommentVO apply(Comment comment) {
                        CommentVO commentVO = BeanCopyUtils.copyBean(comment, CommentVO.class);
                        User user = userService.getById(comment.getUserId());
                        commentVO.setCommentContent(comment.getContent());
                        commentVO.setNickname(user.getNickname());
                        commentVO.setAvatar(user.getAvatar());
                        commentVO.setWebSite(user.getWebSite());
                        commentVO.setCreateTime(comment.getCreateTime());
                        // 根据评论id集合查询回复数据
                        List<ReplyVO> replyVoList = baseMapper.listReplies(comment.getId());
                        ReplyCountVO replyCountVO = baseMapper.listReplyCountByCommentId(comment.getId());
                        commentVO.setReplyCount(Objects.nonNull(replyCountVO) ? replyCountVO.getReplyCount() : REPLY_COUNT);
                        commentVO.setReplyVoList(replyVoList);
                        return commentVO;
                    }
                }).collect(Collectors.toList());

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("commentCount",commentCount);
        resultMap.put("commentDTOList", commentVOList);
        return ResponseResult.okResult(resultMap);
    }

    /**
     * 查看更多回复
     * @param pageNo
     * @param pageSize
     * @param commentId
     * @return
     */
    @Override
    public ResponseResult repliesByComId(Integer pageNo, Integer pageSize, Long commentId) {
        //添加条件
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Objects.nonNull(commentId), Comment::getParentId, commentId);
        Page<Comment> page = new Page<>(pageNo, pageSize);
        page(page, queryWrapper);
        List<Comment> commentList = page.getRecords();
        List<ReplyVO> replyVOList = commentList.stream()
                .map(new Function<Comment, ReplyVO>() {
                    @Override
                    public ReplyVO apply(Comment comment) {
                        ReplyVO replyVO = BeanCopyUtils.copyBean(comment, ReplyVO.class);
                        User user = userService.getById(comment.getUserId());
                        User replyUser = userService.getById(comment.getReplyUserId());
                        replyVO.setNickname(user.getNickname());
                        replyVO.setAvatar(user.getAvatar());
                        replyVO.setWebSite(user.getWebSite());
                        replyVO.setReplyNickname(replyUser.getNickname());
                        replyVO.setReplyWebSite(replyUser.getWebSite());
                        return replyVO;
                    }
                }).collect(Collectors.toList());

        return ResponseResult.okResult(replyVOList);
    }

    /**
     * 后台获取评论列表
     * @param pageNo
     * @param pageSize
     * @param keywords
     * @return
     */
    @Override
    public ResponseResult list(Integer pageNo, Integer pageSize, String keywords) {
        LambdaQueryWrapper<Comment> commentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(keywords)) {
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.like(User::getNickname, keywords);
            List<User> userList = userService.list(queryWrapper);
            List<Long> ids = userList.stream().map(user -> user.getId()).collect(Collectors.toList());
            commentLambdaQueryWrapper.in(Comment::getUserId, ids)
                    .or().in(Comment::getReplyUserId, ids);
        }
        Page<Comment> page = new Page<>(pageNo, pageSize);
        page(page, commentLambdaQueryWrapper);
        List<Comment> records = page.getRecords();
        List<SystemCommentVO> systemCommentVOS = records.stream()
                .map(new Function<Comment, SystemCommentVO>() {
                    @Override
                    public SystemCommentVO apply(Comment comment) {
                        SystemCommentVO systemCommentVO = BeanCopyUtils.copyBean(comment, SystemCommentVO.class);
                        User user = userService.getById(comment.getUserId());
                        systemCommentVO.setAvatar(user.getAvatar());
                        systemCommentVO.setNickname(user.getNickname());
                        User replyUser = userService.getById(comment.getReplyUserId());
                        systemCommentVO.setReplyNickname(replyUser.getNickname());
                        Article article = articleService.getById(comment.getArticleId());
                        systemCommentVO.setArticleTitle(article.getTitle());
                        return systemCommentVO;
                    }
                }).collect(Collectors.toList());
        Page<SystemCommentVO> systemCommentVOPage = new Page<>();
        BeanUtils.copyProperties(page, systemCommentVOPage, "records");
        systemCommentVOPage.setRecords(systemCommentVOS);
        return ResponseResult.okResult(systemCommentVOPage);
    }

    /**
     * 删除评论
     * @param ids
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult remove(List<Long> ids) {
        int i = baseMapper.deleteBatchIds(ids);
        return i > 0 ? ResponseResult.okResult() : ResponseResult.errorResult("删除失败！");
    }

    /**
     * 用户评论
     * @param commentDTO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult addComment(CommentDTO commentDTO) {
        if (commentDTO.getUserId() != null) {
            throw new SystemException("非法请求评论!");
        }
        // 过滤标签
        commentDTO.setCommentContent(HTMLUtils.deleteTag(commentDTO.getCommentContent()));
        Comment comment = Comment.builder()
                .userId(BaseContext.getCurrentId())
                .replyUserId(commentDTO.getReplyUserId())
                .articleId(commentDTO.getArticleId())
                .content(commentDTO.getCommentContent())
                .parentId(commentDTO.getParentId())
                .build();
        int rows = baseMapper.insert(comment);
       /* // 判断是否开启邮箱通知,通知用户
        if (websiteConfig.getIsEmailNotice().equals(TRUE)) {
            notice(comment);
        }*/
        return rows > 0 ? ResponseResult.okResult(comment) : ResponseResult.errorResult("评论失败");
    }
}


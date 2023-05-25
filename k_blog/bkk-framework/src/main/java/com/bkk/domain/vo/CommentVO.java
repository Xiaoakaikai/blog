package com.bkk.domain.vo;

import com.bkk.domain.entity.Comment;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * 评论
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("CommentVO")
public class CommentVO {

    /**
     * 评论id
     */
    @ApiModelProperty("评论id")
    private Long id;

    /**
     * 用户id
     */
    @ApiModelProperty("用户id")
    private Long userId;

    /**
     * 用户昵称
     */
    @ApiModelProperty("用户昵称")
    private String nickname;

    /**
     * 用户头像
     */
    @ApiModelProperty("用户头像")
    private String avatar;

    /**
     * 个人网站
     */
    @ApiModelProperty("个人网站")
    private String webSite;

    /**
     * 评论内容
     */
    @ApiModelProperty("评论内容")
    private String commentContent;

    /**
     * 评论时间
     */
    @ApiModelProperty("评论时间")
    private LocalDateTime createTime;

    /**
     * 点赞数
     */
    @ApiModelProperty("点赞数")
    private Integer likeCount;

    /**
     * 回复量
     */
    @ApiModelProperty("回复量")
    private Integer replyCount;

    /**
     * 回复列表
     */
    @ApiModelProperty("回复列表")
    private List<ReplyVO> replyVoList;

}

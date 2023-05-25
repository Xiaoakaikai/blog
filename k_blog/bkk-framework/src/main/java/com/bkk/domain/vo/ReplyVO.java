package com.bkk.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("ReplyVO")
public class ReplyVO {

    /**
     * 评论id
     */
    @ApiModelProperty("评论id")
    private Long id;

    /**
     * 父评论id
     */
    @ApiModelProperty("父评论id")
    private Long parentId;

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
     * 被回复用户id
     */
    @ApiModelProperty("被回复用户id")
    private Long replyUserId;

    /**
     * 被回复用户昵称
     */
    @ApiModelProperty("被回复用户昵称")
    private String replyNickname;

    /**
     * 被回复个人网站
     */
    @ApiModelProperty("被回复个人网站")
    private String replyWebSite;

    /**
     * 评论内容
     */
    @ApiModelProperty("评论内容")
    private String content;

    /**
     * 点赞数
     */
    @ApiModelProperty("点赞数")
    private Integer likeCount;

    /**
     * 评论时间
     */
    @ApiModelProperty("评论时间")
    private LocalDateTime createTime;

}
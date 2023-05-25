package com.bkk.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 评论信息
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("SystemCommentVO")
public class SystemCommentVO {

    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    @ApiModelProperty(value = "回复人昵称")
    private String replyNickname;

    @ApiModelProperty(value = "文章标题")
    private String articleTitle;

    @ApiModelProperty(value = "评论内容")
    private String content;

    @ApiModelProperty(value = "评论时间")
    private LocalDateTime createTime;


}

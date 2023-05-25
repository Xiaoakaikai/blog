package com.bkk.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("ReplyCountVO")
public class ReplyCountVO {

    /**
     * 评论id
     */
    @ApiModelProperty("评论id")
    private Integer commentId;

    /**
     * 回复数量
     */
    @ApiModelProperty("回复数量")
    private Integer replyCount;

}

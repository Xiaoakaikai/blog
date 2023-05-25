package com.bkk.domain.entity;

import java.time.LocalDateTime;
import java.util.Date;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 评论表(Comment)表实体类
 *
 * @author makejava
 * @since 2023-03-30 18:09:51
 */
@SuppressWarnings("serial")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("b_comment")
@ApiModel(value = "Comment对象", description = "评论表")
public class Comment implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "评论人ID")
    private Long userId;

    @ApiModelProperty(value = "文章id")
    private Long articleId;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "回复人id")
    private Long replyUserId;

    @ApiModelProperty(value = "父id")
    private Long parentId;

    @ApiModelProperty(value = "评论时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "删除标志（0代表未删除，1代表已删除）")
    private Integer delFlag;



}

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
import lombok.experimental.Accessors;

/**
 * 博客文章表(Article)表实体类
 *
 * @author makejava
 * @since 2023-03-26 22:06:02
 */
@SuppressWarnings("serial")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("b_article")
@Accessors(chain = true)
@ApiModel(value = "Article对象", description = "文章表")
public class Article implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "分类id")
    private Long categoryId;

    @ApiModelProperty(value = "分类名称")
    @TableField(exist = false)
    private String categoryName;

    @ApiModelProperty(value = "文章标题")
    private String title;

    @ApiModelProperty(value = "文章封面地址")
    private String avatar;

    @ApiModelProperty(value = "文章简介")
    private String summary;

    @ApiModelProperty(value = "文章内容 （最多两百字）")
    private String content;

    @ApiModelProperty(value = "文章内容md版")
    private String contentMd;

    @ApiModelProperty(value = "是否是私密文章 0 否 1是")
    private Integer isSecret;

    @ApiModelProperty(value = "是否置顶 0否 1是")
    private Integer isStick;

    @ApiModelProperty(value= "是否发布 0：下架 1：发布")
    private Integer isPublish;

    @ApiModelProperty(value = "是否原创  0：转载 1:原创")
    private Integer isOriginal;

    @ApiModelProperty(value = "转载地址")
    private String originalUrl;

    @ApiModelProperty(value = "文章阅读量")
    private Integer quantity;

    @ApiModelProperty(value = "说明")
    private String remark;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "seo关键词")
    private String keywords;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "删除标志（0代表未删除，1代表已删除）")
    private Integer delFlag;



}

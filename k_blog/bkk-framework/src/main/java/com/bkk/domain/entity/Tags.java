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
 * 博客标签表(Tags)表实体类
 *
 * @author makejava
 * @since 2023-03-27 16:54:59
 */
@SuppressWarnings("serial")
@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName("b_tags")
@ApiModel(value = "Tags对象", description = "标签表")
public class Tags implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "标签名称")
    private String name;

    @ApiModelProperty(value = "点赞量")
    private Integer clickVolume;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "最后更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "删除标志（0代表未删除，1代表已删除）")
    private Integer delFlag;

    @TableField(exist = false)
    private Integer articleCount;


}

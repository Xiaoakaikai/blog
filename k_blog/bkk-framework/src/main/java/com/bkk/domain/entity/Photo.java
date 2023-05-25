package com.bkk.domain.entity;

import java.time.LocalDateTime;
import java.util.Date;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 照片(Photo)表实体类
 *
 * @author makejava
 * @since 2023-04-13 21:26:21
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("b_photo")
@ApiModel(value = "Photo对象", description = "照片表")
public class Photo implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "相册id")
    private Long albumId;

    @ApiModelProperty(value = "照片名")
    private String name;

    @ApiModelProperty(value = "照片描述")
    private String info;

    @ApiModelProperty(value = "照片地址")
    private String url;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "删除标志（0代表未删除，1代表已删除）")
    private Integer delFlag;



}

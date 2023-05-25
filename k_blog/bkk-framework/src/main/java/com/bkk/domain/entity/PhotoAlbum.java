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
 * 相册(PhotoAlbum)表实体类
 *
 * @author makejava
 * @since 2023-04-13 21:22:47
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("b_photo_album")
@ApiModel(value = "PhotoAlbum对象", description = "相册表")
public class PhotoAlbum implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "相册名")
    private String name;

    @ApiModelProperty(value = "相册描述")
    private String info;

    @ApiModelProperty(value = "相册封面")
    private String cover;

    @ApiModelProperty(value = "状态值 0公开 1私密")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "删除标志（0代表未删除，1代表已删除）")
    private Integer delFlag;

    @TableField(exist = false)
    private Integer photoCount;

}

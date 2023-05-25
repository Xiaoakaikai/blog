package com.bkk.domain.entity;

import java.time.LocalDateTime;
import java.util.Date;

import java.io.Serializable;
import java.util.List;

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
 * 系统管理-角色表 (Role)表实体类
 *
 * @author makejava
 * @since 2023-04-11 21:42:18
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("b_role")
@ApiModel(value = "Role对象", description = "角色表")
public class Role implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "角色编码")
    private String code;

    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "角色描述")
    private String remarks;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "删除标志（0代表未删除，1代表已删除）")
    private Integer delFlag;

    @TableField(exist = false)
    private List<Long> menus;

}

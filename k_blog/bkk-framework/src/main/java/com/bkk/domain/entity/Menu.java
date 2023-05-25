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
 * 系统管理-权限资源表 (Menu)表实体类
 *
 * @author makejava
 * @since 2023-04-07 18:05:33
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("b_menu")
@ApiModel(value = "Menu对象", description = "菜单表")
public class Menu implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "上级资源ID")
    private Long parentId;

    @ApiModelProperty(value = "url")
    private String url;

    @ApiModelProperty(value = "资源编码")
    private String component;

    @ApiModelProperty(value = "资源名称")
    private String title;

    @ApiModelProperty(value = "资源级别")
    private Integer level;

    @ApiModelProperty(value = "排序")
    private Integer sortNo;

    @ApiModelProperty(value = "资源图标")
    private String icon;

    @ApiModelProperty(value = "类型 menu、button")
    private String type;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    //重定向地址
//    private String redirect;

    @ApiModelProperty(value = "跳转地址")
    private String name;

    @ApiModelProperty(value = "是否隐藏")
    private Integer hidden;

    @ApiModelProperty(value = "删除标志（0代表未删除，1代表已删除）")
    private Integer delFlag;

    @TableField(exist = false)
    private List<Menu> children;

}

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
 * 系统管理-用户基础信息表(User)表实体类
 *
 * @author makejava
 * @since 2023-03-28 16:29:14
 */
@SuppressWarnings("serial")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("b_user")
@ApiModel(value = "User对象", description = "用户表")
public class User implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    @ApiModelProperty(value = "账号")
    private String username;

    @ApiModelProperty(value = "登录密码")
    private String password;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @ApiModelProperty(value = "个人网站")
    private String webSite;

    @ApiModelProperty(value = "用户简介")
    private String intro;

    @ApiModelProperty(value = "邮箱号")
    private String email;

    @ApiModelProperty(value = "是否禁用(0:禁用  1:正常)")
    private Integer isDisable;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "登录方式")
    private Integer loginType;

    @ApiModelProperty(value = "角色id")
    private Long roleId;

    @ApiModelProperty(value = "ip地址")
    private String ipAddress;

    @ApiModelProperty(value = "ip来源")
    private String ipSource;

    @ApiModelProperty(value = "登录系统")
    private String os;

    @ApiModelProperty(value = "最后登录时间")
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime lastLoginTime;

    @ApiModelProperty(value = "浏览器")
    private String browser;

    @ApiModelProperty(value = "删除标志（0代表未删除，1代表已删除）")
    private Integer delFlag;



}

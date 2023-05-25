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
 * 日志表(UserLog)表实体类
 *
 * @author makejava
 * @since 2023-04-14 15:36:59
 */
@SuppressWarnings("serial")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("b_user_log")
@ApiModel(value = "UserLog对象", description = "用户日志表")
public class UserLog implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "操作用户ID")
    private Long userId;

    @ApiModelProperty(value = "ip地址")
    private String ip;

    @ApiModelProperty(value = "操作地址")
    private String address;

    @ApiModelProperty(value = "操作类型")
    private String type;

    @ApiModelProperty(value = "操作日志")
    private String description;

    @ApiModelProperty(value = "操作模块")
    private String model;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "操作结果")
    private String result;

    @ApiModelProperty(value = "操作系统")
    private String accessOs;

    @ApiModelProperty(value = "浏览器")
    private String browser;

    @ApiModelProperty(value = "客户端类型")
    private String clientType;

    @ApiModelProperty(value = "删除标志（0代表未删除，1代表已删除）")
    private Integer delFlag;



}

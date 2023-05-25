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
 * (ExceptionLog)表实体类
 *
 * @author makejava
 * @since 2023-04-14 15:36:59
 */
@SuppressWarnings("serial")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("b_exception_log")
@ApiModel(value = "ExceptionLog对象", description = "异常日志表")
public class ExceptionLog implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "IP")
    private String ip;

    @ApiModelProperty(value = "ip来源")
    private String ipSource;

    @ApiModelProperty(value = "请求方法")
    private String method;

    @ApiModelProperty(value = "描述")
    private String operation;

    @ApiModelProperty(value = "参数")
    private String params;

    @ApiModelProperty(value = "异常对象json格式")
    private String exceptionJson;

    @ApiModelProperty(value = "异常简单信息,等同于e.getMessage")
    private String exceptionMessage;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "删除标志（0代表未删除，1代表已删除）")
    private Integer delFlag;



}

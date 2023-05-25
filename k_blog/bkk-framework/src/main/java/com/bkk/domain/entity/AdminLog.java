package com.bkk.domain.entity;

import java.time.LocalDateTime;
import java.util.Date;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (AdminLog)表实体类
 *
 * @author makejava
 * @since 2023-04-14 15:36:58
 */
@SuppressWarnings("serial")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("b_admin_log")
@ApiModel(value = "AdminLog对象", description = "操作日志表")
public class AdminLog implements Serializable {

    private static final long serialVersionUID=1L;
    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "操作用户")
    private String username;

    @ApiModelProperty(value = "请求接口")
    private String requestUrl;

    @ApiModelProperty(value = "请求方式")
    private String type;

    @ApiModelProperty(value = "操作名称")
    private String operationName;

    @ApiModelProperty(value = "ip")
    private String ip;

    @ApiModelProperty(value = "ip来源")
    private String source;

    @ApiModelProperty(value = "请求参数")
    private String paramsJson;

    @ApiModelProperty(value = "类地址")
    private String classPath;

    @ApiModelProperty(value = "方法名")
    private String methodName;

    @ApiModelProperty(value = "请求接口耗时")
    private Long spendTime;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "删除标志（0代表未删除，1代表已删除）")
    private Integer delFlag;



}

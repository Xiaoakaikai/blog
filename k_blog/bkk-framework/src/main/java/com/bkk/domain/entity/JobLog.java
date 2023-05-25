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
 * 定时任务调度日志表(JobLog)表实体类
 *
 * @author makejava
 * @since 2023-04-14 15:36:59
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("b_job_log")
@ApiModel(value = "JobLog对象", description = "任务日志表")
public class JobLog implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "任务日志ID")
    private Long id;

    @ApiModelProperty(value = "任务ID")
    private Long jobId;

    @ApiModelProperty(value = "任务名称")
    private String jobName;

    @ApiModelProperty(value = "任务组名")
    private String jobGroup;

    @ApiModelProperty(value = "调用目标字符串")
    private String invokeTarget;

    @ApiModelProperty(value = "日志信息")
    private String jobMessage;

    @ApiModelProperty(value = "执行状态（0正常 1失败）")
    private String status;

    @ApiModelProperty(value = "异常信息")
    private String exceptionInfo;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    @ApiModelProperty(value = "结束时间")
    private Date stopTime;

    @ApiModelProperty(value = "删除标志（0代表未删除，1代表已删除）")
    private Integer delFlag;



}

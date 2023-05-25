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
 * (Message)表实体类
 *
 * @author makejava
 * @since 2023-04-13 20:25:46
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("b_message")
@ApiModel(value = "Message对象", description = "留言表")
public class Message implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "ID")
    private Long id;


    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @ApiModelProperty(value = "ip地址")
    private String ipAddress;

    @ApiModelProperty(value = "ip来源")
    private String ipSource;

    private Integer time;

    @ApiModelProperty(value = "状态 0:审核  1：正常")
    private Integer status;



}

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
 * 友情链接表(FriendLink)表实体类
 *
 * @author makejava
 * @since 2023-03-30 17:31:43
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("b_friend_link")
@ApiModel(value = "FriendLink对象", description = "友链表")
public class FriendLink implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "网站名称")
    private String name;

    @ApiModelProperty(value = "网站地址")
    private String url;

    @ApiModelProperty(value = "网站头像地址")
    private String avatar;

    @ApiModelProperty(value = "网站描述")
    private String info;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "下架原因")
    private String reason;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "ENUM-状态:\"0-待审核\",\"1-通过\"")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "删除标志（0代表未删除，1代表已删除）")
    private Integer delFlag;



}

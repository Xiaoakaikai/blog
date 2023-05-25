package com.bkk.domain.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author xak
 */
@Data
@ApiModel("UserVO")
public class UserVO {

    private Long id;

    @ApiModelProperty(value = "状态")
    private Integer isDisable;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "最后更新时间")
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "最后登录时间")
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime lastLoginTime;

    @ApiModelProperty(value = "角色ID")
    private Long roleId;

    @ApiModelProperty(value = "IP地址")
    private String ipAddress;

    @ApiModelProperty(value = "IP来源")
    private String ipSource;

    @ApiModelProperty(value = "登录方式")
    private Integer loginType;

    /**
     * 昵称
     * */
    @ApiModelProperty(value = "昵称")
    private String nickname;

    /**
     * 头像
     * */
    @ApiModelProperty(value = "头像")
    private String avatar;
}
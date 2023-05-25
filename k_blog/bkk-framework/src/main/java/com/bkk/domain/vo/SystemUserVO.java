package com.bkk.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *  系统用户出参
 */
@Data
@ApiModel("SystemUserVO")
public class SystemUserVO {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "账号")
    private String username;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "状态")
    private Integer isDisable;

    @ApiModelProperty(value = "角色Id")
    private Long roleId;
}

package com.bkk.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("OnlineUserVo")
public class OnlineUserVo {

    @ApiModelProperty("登录id")
    private String loginId;
    @ApiModelProperty("用户id")
    private Long userId;
    @ApiModelProperty("昵称")
    private String nickname;
    @ApiModelProperty("头像地址")
    private String avatar;
    @ApiModelProperty("ip")
    private String ip;
    @ApiModelProperty("操作系统")
    private String os;
    @ApiModelProperty("登录城市")
    private String city;
    @ApiModelProperty("浏览器")
    private String browser;
    @ApiModelProperty("token")
    private String tokenValue;
    /**
     * 登录时间
     */
    @ApiModelProperty("登录时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date loginTime;
    /**
     * 最近一次操作时间
     */
    @ApiModelProperty("最近一次操作时间")
    private Date lastActivityTime;

    //过期时间
    private Long expireTime;
}

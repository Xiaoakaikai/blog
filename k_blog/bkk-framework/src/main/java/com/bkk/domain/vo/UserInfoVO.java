package com.bkk.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("UserInfoVO")
public class UserInfoVO {

    /**
     * 用户账号id
     */
    @ApiModelProperty("用户账号id")
    private Long id;

    /**
     * 邮箱号
     */
    @ApiModelProperty("邮箱号")
    private String email;

    /**
     * 登录方式
     */
    @ApiModelProperty("登录方式")
    private Integer loginType;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String username;

    /**
     * 用户昵称
     */
    @ApiModelProperty("用户昵称")
    private String nickname;

    /**
     * 用户头像
     */
    @ApiModelProperty("用户头像")
    private String avatar;

    /**
     * 用户简介
     */
    @ApiModelProperty("用户简介")
    private String intro;

    /**
     * 个人网站
     */
    @ApiModelProperty("个人网站")
    private String webSite;

    /**
     * 点赞文章集合
     */
    @ApiModelProperty("点赞文章集合")
    private Set<Object> articleLikeSet;

    /**
     * 点赞评论集合
     */
    @ApiModelProperty("点赞评论集合")
    private Set<Object> commentLikeSet;

    /**
     * 用户登录ip
     */
    @ApiModelProperty("用户登录ip")
    private String ipAddress;

    /**
     * ip来源
     */
    @ApiModelProperty("ip来源")
    private String ipSource;

    /**
     * 最近登录时间
     */
    @ApiModelProperty("最近登录时间")
    private LocalDateTime lastLoginTime;

    /**
     * token
     */
    @ApiModelProperty("token")
    private String token;

}

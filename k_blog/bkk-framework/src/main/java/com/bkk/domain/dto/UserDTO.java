package com.bkk.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    /**
     * 昵称
     * */
    private String nickname;
    /**
     * 简介
     * */
    private String intro;

    /**
     * 个人网站
     * */
    private String webSite;

    /**
     * 头像
     * */
    private String avatar;

    /**
     * 邮箱
     * */
    private String email;

    /**
     * 验证码
     * */
    private String code;
}

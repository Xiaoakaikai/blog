package com.bkk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.dto.EmailRegisterDTO;
import com.bkk.domain.dto.UserDTO;
import com.bkk.domain.entity.User;
import com.bkk.domain.vo.SystemUserVO;

import java.util.List;
import java.util.Map;

public interface UserService extends IService<User> {
    ResponseResult updateUser(UserDTO userDTO);

    ResponseResult emailRegister(EmailRegisterDTO dto);

    ResponseResult sendEmailCode(String email);

    ResponseResult bindEmail(UserDTO userDTO);

    ResponseResult getCurrentUserInfo();

    ResponseResult getUserMenu();

    ResponseResult list(Integer pageNo, Integer pageSize, String username, Integer loginType);

    ResponseResult info(Long id);

    ResponseResult update(SystemUserVO user);

    ResponseResult remove(List<Long> ids);

    ResponseResult online(Integer pageNo, Integer pageSize, String name);

    ResponseResult kick(String token);

    ResponseResult updatePassword(Map<String, String> map);

    ResponseResult blogUpdatePassword(EmailRegisterDTO dto);
}
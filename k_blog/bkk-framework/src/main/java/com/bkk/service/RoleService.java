package com.bkk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.entity.Role;

import java.util.List;


/**
 * 系统管理-角色表 (Role)表服务接口
 *
 * @author makejava
 * @since 2023-04-11 21:42:19
 */
public interface RoleService extends IService<Role> {

    ResponseResult list(Integer pageNo, Integer pageSize, String name);

    ResponseResult create(Role role);

    ResponseResult info(Long roleId);

    ResponseResult update(Role role);

    ResponseResult remove(List<Long> ids);
}


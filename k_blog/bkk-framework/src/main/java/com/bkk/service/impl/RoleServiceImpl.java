package com.bkk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.entity.Role;
import com.bkk.mapper.RoleMapper;
import com.bkk.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * 系统管理-角色表 (Role)表服务实现类
 *
 * @author makejava
 * @since 2023-04-11 21:42:19
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 角色列表
     * @param name
     * @return
     */
    @Override
    public ResponseResult list(Integer pageNo, Integer pageSize, String name) {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Objects.nonNull(name) && !name.equals(""), Role::getName, name);
        Page<Role> page = new Page(pageNo, pageSize);
        page(page, queryWrapper);
        return ResponseResult.okResult(page);
    }

    /**
     * 新增角色
     * @param role
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult create(Role role) {
        baseMapper.insert(role);
        roleMapper.insertBatchByRole(role.getMenus(), role.getId());
        return ResponseResult.okResult();
    }

    /**
     * 获取该角色所有的权限
     * @param roleId
     * @return
     */
    @Override
    public ResponseResult info(Long roleId) {
        List<Long> list = baseMapper.queryByRoleMenu(roleId);
        return ResponseResult.okResult(list);
    }

    /**
     * 修改角色
     * @param role
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult update(Role role) {
        baseMapper.updateById(role);

        //先删所有权限在新增
        baseMapper.delByRoleId(role.getId(),null);
        baseMapper.insertBatchByRole(role.getMenus(), role.getId());
        return ResponseResult.okResult();
    }

    /**
     * 删除角色
     * @param ids
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult remove(List<Long> ids) {
        baseMapper.deleteBatchIds(ids);
        ids.forEach(id -> baseMapper.delByRoleId(id, null));
        return ResponseResult.okResult();
    }

}


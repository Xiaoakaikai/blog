package com.bkk.mapper;
import com.bkk.domain.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 系统管理-角色表 (Role)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-11 21:42:18
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    List<Long> queryByRoleMenu(Long roleId);

    void insertBatchByRole(@Param("menus") List<Long> menus, @Param("roleId") Long roleId);

    void delByRoleId(@Param("roleId") Long roleId,@Param("menus") List<Long> menus);

}

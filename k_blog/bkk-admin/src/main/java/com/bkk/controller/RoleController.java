package com.bkk.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bkk.annotation.OperationLogger;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.entity.Role;
import com.bkk.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
@Api(tags = "角色管理")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/list")
    @ApiOperation(value = "角色列表", httpMethod = "GET", response = ResponseResult.class, notes = "角色列表")
    public ResponseResult<Page<Role>> list(Integer pageNo, Integer pageSize, String name) {
        return roleService.list(pageNo, pageSize, name);
    }

    @OperationLogger(value = "添加角色")
    @PostMapping("/create")
    @ApiOperation(value = "添加角色", httpMethod = "POST", response = ResponseResult.class, notes = "添加角色")
    public ResponseResult create(@RequestBody Role role) {
        return roleService.create(role);
    }

    @GetMapping("/info")
    @ApiOperation(value = "角色详情", httpMethod = "GET", response = ResponseResult.class, notes = "角色详情")
    public ResponseResult<List<Long>> info(@RequestParam Long roleId) {
        return roleService.info(roleId);
    }

    @OperationLogger(value = "修改角色")
    @PutMapping("/update")
    @ApiOperation(value = "修改角色", httpMethod = "PUT", response = ResponseResult.class, notes = "修改角色")
    public ResponseResult update(@RequestBody Role role) {
        return roleService.update(role);
    }

    @OperationLogger(value = "删除角色")
    @DeleteMapping("/remove")
    @ApiOperation(value = "删除角色", httpMethod = "DELETE", response = ResponseResult.class, notes = "删除角色")
    public ResponseResult remove(@RequestBody List<Long> ids) {
        return roleService.remove(ids);
    }
}

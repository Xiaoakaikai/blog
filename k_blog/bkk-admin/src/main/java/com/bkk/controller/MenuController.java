package com.bkk.controller;

import com.bkk.annotation.OperationLogger;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.entity.Menu;
import com.bkk.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
@Api(tags = "菜单管理")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/getMenuTree")
    @ApiOperation(value = "获取菜单树", httpMethod = "GET", response = ResponseResult.class, notes = "获取菜单树")
    public ResponseResult<List<Menu>> getMenuTree() {
        return menuService.getMenuTree();
    }

    @OperationLogger(value = "添加菜单")
    @PostMapping("/create")
    @ApiOperation(value = "添加菜单", httpMethod = "POST", response = ResponseResult.class, notes = "添加菜单")
    public ResponseResult create(@RequestBody Menu menu) {
        return menuService.create(menu);
    }

    @OperationLogger(value = "修改菜单")
    @PutMapping("/update")
    @ApiOperation(value = "修改菜单", httpMethod = "PUT", response = ResponseResult.class, notes = "修改菜单")
    public ResponseResult update(@RequestBody Menu menu) {
        return menuService.update(menu);
    }

    @OperationLogger(value = "删除菜单")
    @DeleteMapping("/remove")
    @ApiOperation(value = "删除菜单", httpMethod = "DELETE", response = ResponseResult.class, notes = "删除菜单")
    public ResponseResult remove(@RequestParam Long id) {
        return menuService.remove(id);
    }
}

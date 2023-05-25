package com.bkk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.entity.Menu;

import java.util.List;


/**
 * 系统管理-权限资源表 (Menu)表服务接口
 *
 * @author makejava
 * @since 2023-04-07 18:05:34
 */
public interface MenuService extends IService<Menu> {

    List<Menu> listMenuTree(List<Menu> menus);

    ResponseResult getMenuTree();

    ResponseResult create(Menu menu);

    ResponseResult update(Menu menu);

    ResponseResult remove(Long id);
}


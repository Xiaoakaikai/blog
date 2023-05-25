package com.bkk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.entity.Menu;
import com.bkk.mapper.MenuMapper;
import com.bkk.service.MenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统管理-权限资源表 (Menu)表服务实现类
 *
 * @author makejava
 * @since 2023-04-07 18:05:34
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    /**
     * 获取菜单树
     * @param menus
     * @return
     */
    @Override
    public List<Menu> listMenuTree(List<Menu> menus) {
        List<Menu> resultList = new ArrayList<>();
        for (Menu menu : menus) {
            Long parentId = menu.getParentId();
            if ( parentId == null || parentId == 0)
                resultList.add(menu);
        }
        for (Menu menu : resultList) {
            menu.setChildren(getChild(menu.getId(),menus));
        }
        return resultList;
    }

    /**
     * 菜单管理获取菜单树
     * @return
     */
    @Override
    public ResponseResult getMenuTree() {
        List<Menu> menus = listMenuTree(baseMapper.selectList(null));
        return ResponseResult.okResult(menus);
    }

    /**
     * 新增菜单
     * @param menu
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult create(Menu menu) {
        int insert = baseMapper.insert(menu);
        return insert > 0 ? ResponseResult.okResult() : ResponseResult.errorResult("添加失败！");
    }

    /**
     * 修改菜单
     * @param menu
     * @return
     */
    @Override
    public ResponseResult update(Menu menu) {
        int update = baseMapper.updateById(menu);
        return update > 0 ? ResponseResult.okResult() : ResponseResult.errorResult("修改失败！");
    }

    /**
     * 根据id删除菜单
     * @param id
     * @return
     */
    @Override
    public ResponseResult remove(Long id) {
        int delete = baseMapper.deleteById(id);
        return delete > 0 ? ResponseResult.okResult() : ResponseResult.errorResult("删除失败！");
    }


    //----------------自定义方法开始------------
    private List<Menu> getChild(Long pid , List<Menu> menus){
        List<Menu> childrens = new ArrayList<>();
        for (Menu e: menus) {
            Long parentId = e.getParentId();
            if(parentId != null && parentId.equals(pid)){
                // 子菜单的下级菜单
                childrens.add( e );
            }
        }
        // 把子菜单的子菜单再循环一遍
        for (Menu e: childrens) {
            // childrens
            e.setChildren(getChild(e.getId(),menus));
        }
        //停下来的条件，如果 没有子元素了，则停下来
        if( childrens.size()==0 ){
            return null;
        }
        return childrens;
    }
}


package com.bkk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.entity.Category;

import java.util.List;


/**
 * 博客分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2023-03-27 15:43:03
 */
public interface CategoryService extends IService<Category> {

    ResponseResult categoryList();

    ResponseResult list(Integer pageNo, Integer pageSize, String name);

    ResponseResult add(Category category);

    ResponseResult info(Long id);

    ResponseResult update(Category category);

    ResponseResult top(Long id);

    ResponseResult delete(Long id);

    ResponseResult deleteBatch(List<Category> categoryList);
}


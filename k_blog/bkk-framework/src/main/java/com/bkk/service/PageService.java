package com.bkk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.entity.Page;


/**
 * (Page)表服务接口
 *
 * @author makejava
 * @since 2023-03-30 16:45:40
 */
public interface PageService extends IService<Page> {

    ResponseResult PageList();

    ResponseResult add(Page page);

    ResponseResult update(Page page);

    ResponseResult delete(Long id);
}


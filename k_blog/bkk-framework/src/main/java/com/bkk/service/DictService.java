package com.bkk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.entity.Dict;


/**
 * 字典表(Dict)表服务接口
 *
 * @author makejava
 * @since 2023-04-11 20:39:40
 */
public interface DictService extends IService<Dict> {

    ResponseResult list(Integer pageNo, Integer pageSize, String name, Integer isPublish);
}


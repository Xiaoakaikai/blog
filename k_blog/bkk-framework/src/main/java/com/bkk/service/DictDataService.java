package com.bkk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.entity.DictData;

import java.util.List;


/**
 * (DictData)表服务接口
 *
 * @author makejava
 * @since 2023-04-11 20:43:15
 */
public interface DictDataService extends IService<DictData> {

    ResponseResult getDataByDictType(List<String> types);
}


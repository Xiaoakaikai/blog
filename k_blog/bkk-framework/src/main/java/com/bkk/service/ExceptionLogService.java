package com.bkk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.entity.ExceptionLog;

import java.util.List;


/**
 * (ExceptionLog)表服务接口
 *
 * @author makejava
 * @since 2023-04-14 15:36:59
 */
public interface ExceptionLogService extends IService<ExceptionLog> {

    ResponseResult list(Integer pageNo, Integer pageSize);

    ResponseResult delete(List<Long> ids);
}


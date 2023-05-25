package com.bkk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.entity.AdminLog;

import java.util.List;


/**
 * (AdminLog)表服务接口
 *
 * @author makejava
 * @since 2023-04-14 15:36:59
 */
public interface AdminLogService extends IService<AdminLog> {

    ResponseResult list(Integer pageNo, Integer pageSize);

    ResponseResult delete(List<Long> ids);
}


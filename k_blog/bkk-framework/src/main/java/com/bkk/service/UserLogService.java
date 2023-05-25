package com.bkk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.entity.UserLog;

import java.util.List;


/**
 * 日志表(UserLog)表服务接口
 *
 * @author makejava
 * @since 2023-04-14 15:36:59
 */
public interface UserLogService extends IService<UserLog> {

    ResponseResult list(Integer pageNo, Integer pageSize);

    ResponseResult delete(List<Long> ids);
}


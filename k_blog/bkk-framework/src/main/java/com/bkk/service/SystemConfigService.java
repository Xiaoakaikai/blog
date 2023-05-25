package com.bkk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.entity.SystemConfig;


/**
 * 系统配置表(SystemConfig)表服务接口
 *
 * @author makejava
 * @since 2023-05-22 15:11:36
 */
public interface SystemConfigService extends IService<SystemConfig> {

    SystemConfig getCustomizeOne();

    ResponseResult getConfig();

    ResponseResult updateConfig(SystemConfig systemConfig);
}


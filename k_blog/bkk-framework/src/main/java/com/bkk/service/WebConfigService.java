package com.bkk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.entity.WebConfig;


/**
 * 网站配置表(WebConfig)表服务接口
 *
 * @author makejava
 * @since 2023-03-30 16:25:43
 */
public interface WebConfigService extends IService<WebConfig> {

    ResponseResult webSiteInfo();

    ResponseResult webConfigList();

    ResponseResult update(WebConfig webConfig);
}


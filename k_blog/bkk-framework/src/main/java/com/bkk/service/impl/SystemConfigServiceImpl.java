package com.bkk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.entity.SystemConfig;
import com.bkk.mapper.SystemConfigMapper;
import com.bkk.service.SystemConfigService;
import org.springframework.stereotype.Service;

import static com.bkk.constants.SqlConf.LIMIT_ONE;

/**
 * 系统配置表(SystemConfig)表服务实现类
 *
 * @author makejava
 * @since 2023-05-22 15:11:36
 */
@Service("systemConfigService")
public class SystemConfigServiceImpl extends ServiceImpl<SystemConfigMapper, SystemConfig> implements SystemConfigService {

    /**
     * 获取系统配置信息
     * @return
     */
    @Override
    public ResponseResult getConfig() {
        SystemConfig systemConfig = getCustomizeOne();
        return ResponseResult.okResult(systemConfig);
    }

    /**
     * 更新系统配置信息
     * @return
     */
    @Override
    public ResponseResult updateConfig(SystemConfig systemConfig) {
        boolean update = updateById(systemConfig);
        return update ? ResponseResult.okResult() : ResponseResult.errorResult("更新失败！");
    }

    @Override
    public SystemConfig getCustomizeOne() {
        SystemConfig systemConfig = getOne(new LambdaQueryWrapper<SystemConfig>().last(LIMIT_ONE));
        return systemConfig;
    }
}


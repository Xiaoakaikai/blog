package com.bkk.controller;

import com.bkk.domain.ResponseResult;
import com.bkk.domain.entity.SystemConfig;
import com.bkk.service.SystemConfigService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/config")
public class ConfigController {

    @Autowired
    private SystemConfigService systemConfigService;

    @GetMapping("/getConfig")
    @ApiOperation(value = "查询系统配置", httpMethod = "GET", response = ResponseResult.class, notes = "查询系统配置")
    public ResponseResult<SystemConfig> getConfig() {
        return systemConfigService.getConfig();
    }

    @PutMapping("/update")
    public ResponseResult updateConfig(@RequestBody SystemConfig systemConfig) {
        return systemConfigService.updateConfig(systemConfig);
    }
}

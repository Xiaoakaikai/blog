package com.bkk.controller;

import com.bkk.annotation.OperationLogger;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.entity.WebConfig;
import com.bkk.service.WebConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webConfig")
@Api(tags = "网站配置管理")
public class WebConfigController {

    @Autowired
    private WebConfigService webConfigService;

    @GetMapping("/list")
    @ApiOperation(value = "网站配置列表", httpMethod = "GET", response = ResponseResult.class, notes = "网站配置列表")
    public ResponseResult<WebConfig> list() {
        return webConfigService.webConfigList();
    }

    @OperationLogger(value = "修改网站配置信息")
    @PutMapping("/update")
    @ApiOperation(value = "修改网站配置信息", httpMethod = "PUT", response = ResponseResult.class, notes = "修改网站配置信息")
    public ResponseResult update(@RequestBody WebConfig webConfig) {
        return webConfigService.update(webConfig);
    }
}

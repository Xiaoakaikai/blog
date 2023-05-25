package com.bkk.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.entity.Dict;
import com.bkk.service.DictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dict")
@Api(tags = "字典管理")
public class DictController {

    @Autowired
    private DictService dictService;

    @GetMapping("/list")
    @ApiOperation(value = "字典列表", httpMethod = "GET", response = ResponseResult.class, notes = "字典列表")
    public ResponseResult<Page<Dict>> list(Integer pageNo, Integer pageSize, String name, Integer isPublish) {
        return dictService.list(pageNo, pageSize, name, isPublish);
    }
}

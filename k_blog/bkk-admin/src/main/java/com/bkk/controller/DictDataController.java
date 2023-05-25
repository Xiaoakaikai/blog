package com.bkk.controller;

import com.bkk.domain.ResponseResult;
import com.bkk.service.DictDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dictData")
@Api(tags = "字典数据管理")
public class DictDataController {

    @Autowired
    private DictDataService dictDataService;

    @PostMapping("/getDataByDictType")
    @ApiOperation(value = "字典数据", httpMethod = "POST", response = ResponseResult.class, notes = "字典数据")
    public ResponseResult<Map<String, Map<String, Object>>> getDataByDictType(@RequestBody List<String> types) {
        return dictDataService.getDataByDictType(types);
    }
}

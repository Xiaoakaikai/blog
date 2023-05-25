package com.bkk.controller;

import com.bkk.annotation.BusinessLogger;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.vo.TagVO;
import com.bkk.service.TagsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tags")
@Api(tags = "前台标签管理")
public class TagsController {

    @Autowired
    private TagsService tagsService;

    @BusinessLogger(value = "标签-查询标签列表",type = "查询",desc = "查询所有标签")
    @GetMapping("/list")
    @ApiOperation(value = "标签列表", httpMethod = "GET", response = ResponseResult.class, notes = "标签列表")
    public ResponseResult<List<TagVO>> getAllTags() {
        return tagsService.getAllTags();
    }
}

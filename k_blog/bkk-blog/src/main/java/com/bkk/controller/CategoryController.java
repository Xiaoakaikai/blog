package com.bkk.controller;

import com.bkk.annotation.BusinessLogger;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.vo.CategoryVO;
import com.bkk.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
@Api(tags = "前台分类管理")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @BusinessLogger(value = "分类",type = "查询",desc = "查询所有分类")
    @GetMapping("/categoryList")
    @ApiOperation(value = "分类列表", httpMethod = "GET", response = ResponseResult.class, notes = "分类列表")
    public ResponseResult<List<CategoryVO>> categoryList() {
        return categoryService.categoryList();
    }
}

package com.bkk.controller;

import com.bkk.annotation.OperationLogger;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.entity.Category;
import com.bkk.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@Api(tags = "分类管理")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    @ApiOperation(value = "分类列表", httpMethod = "GET", response = ResponseResult.class, notes = "分类列表")
    public ResponseResult<List<Category>> list(Integer pageNo, Integer pageSize, String name) {
        return categoryService.list(pageNo, pageSize, name);
    }

    @OperationLogger(value = "添加分类")
    @PostMapping("/add")
    @ApiOperation(value = "添加分类", httpMethod = "POST", response = ResponseResult.class, notes = "添加分类")
    public ResponseResult add(@RequestBody Category category) {
        return categoryService.add(category);
    }

    @GetMapping("/info")
    @ApiOperation(value = "分类详情", httpMethod = "GET", response = ResponseResult.class, notes = "分类详情")
    public ResponseResult<Category> info(@RequestParam Long id) {
        return categoryService.info(id);
    }

    @OperationLogger(value = "修改分类")
    @PutMapping("/update")
    @ApiOperation(value = "修改分类", httpMethod = "PUT", response = ResponseResult.class, notes = "修改分类")
    public ResponseResult update(@RequestBody Category category) {
        return categoryService.update(category);
    }

    @OperationLogger(value = "置顶分类")
    @GetMapping("/top")
    @ApiOperation(value = "置顶分类", httpMethod = "GET", response = ResponseResult.class, notes = "置顶分类")
    public ResponseResult top(@RequestParam Long id) {
        return categoryService.top(id);
    }

    @OperationLogger(value = "删除文章")
    @DeleteMapping("/delete")
    @ApiOperation(value = "删除文章", httpMethod = "DELETE", response = ResponseResult.class, notes = "删除分类")
    public ResponseResult delete(@RequestParam Long id) {
        return categoryService.delete(id);
    }

    @OperationLogger(value = "批量删除文章")
    @DeleteMapping("/deleteBatch")
    @ApiOperation(value = "批量删除文章", httpMethod = "DELETE", response = ResponseResult.class, notes = "批量删除文章")
    public ResponseResult deleteBatch(@RequestBody List<Category> categoryList) {
        return categoryService.deleteBatch(categoryList);
    }
}

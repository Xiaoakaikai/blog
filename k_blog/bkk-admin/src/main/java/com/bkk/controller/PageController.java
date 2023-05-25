package com.bkk.controller;

import com.bkk.annotation.OperationLogger;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.entity.Page;
import com.bkk.service.PageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/page")
@Api(tags = "页面管理")
public class PageController {

    @Autowired
    private PageService pageService;

    @GetMapping("/list")
    @ApiOperation(value = "页面列表", httpMethod = "GET", response = ResponseResult.class, notes = "页面列表")
    public ResponseResult<List<Page>> list() {
        return pageService.PageList();
    }

    @OperationLogger(value = "添加页面")
    @PostMapping("/add")
    @ApiOperation(value = "添加页面", httpMethod = "POST", response = ResponseResult.class, notes = "添加页面")
    public ResponseResult add(@RequestBody Page page) {
        return pageService.add(page);
    }

    @OperationLogger(value = "修改页面")
    @PutMapping("/update")
    @ApiOperation(value = "修改页面", httpMethod = "PUT", response = ResponseResult.class, notes = "修改页面")
    public ResponseResult update(@RequestBody Page page) {
        return pageService.update(page);
    }

    @OperationLogger(value = "删除页面")
    @DeleteMapping("/delete")
    @ApiOperation(value = "删除页面", httpMethod = "DELETE", response = ResponseResult.class, notes = "删除页面")
    public ResponseResult delete(@RequestParam Long id) {
        return pageService.delete(id);
    }
}

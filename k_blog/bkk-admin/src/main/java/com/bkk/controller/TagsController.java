package com.bkk.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bkk.annotation.OperationLogger;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.entity.Tags;
import com.bkk.domain.vo.TagVO;
import com.bkk.service.TagsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
@Api(tags = "标签管理")
public class TagsController {

    @Autowired
    private TagsService tagsService;

    @GetMapping("/list")
    @ApiOperation(value = "标签列表", httpMethod = "GET", response = ResponseResult.class, notes = "标签列表")
    public ResponseResult<Page<Tags>> list(Integer pageNo, Integer pageSize, String name) {
        return tagsService.list(pageNo, pageSize, name);
    }

    @OperationLogger(value = "添加标签")
    @PostMapping("/add")
    @ApiOperation(value = "添加标签", httpMethod = "POST", response = ResponseResult.class, notes = "添加标签")
    public ResponseResult add(@RequestBody Tags tags) {
        return tagsService.add(tags);
    }

    @GetMapping("/info")
    @ApiOperation(value = "标签详情", httpMethod = "GET", response = ResponseResult.class, notes = "标签详情")
    public ResponseResult<Tags> info(@RequestParam Long id) {
        return tagsService.info(id);
    }

    @OperationLogger(value = "修改标签")
    @PutMapping("/update")
    @ApiOperation(value = "修改标签", httpMethod = "PUT", response = ResponseResult.class, notes = "修改标签")
    public ResponseResult update(@RequestBody Tags tags) {
        return tagsService.update(tags);
    }

    @OperationLogger(value = "置顶标签")
    @GetMapping("/top")
    @ApiOperation(value = "置顶标签", httpMethod = "GET", response = ResponseResult.class, notes = "置顶标签")
    public ResponseResult top(@RequestParam Long id) {
        return tagsService.top(id);
    }

    @OperationLogger(value = "删除标签")
    @DeleteMapping("/remove")
    @ApiOperation(value = "删除标签", httpMethod = "DELETE", response = ResponseResult.class, notes = "删除标签")
    public ResponseResult remove(@RequestParam Long id) {
        return tagsService.remove(id);
    }

    @OperationLogger(value = "批量删除标签")
    @DeleteMapping("/deleteBatch")
    @ApiOperation(value = "批量删除标签", httpMethod = "DELETE", response = ResponseResult.class, notes = "批量删除标签")
    public ResponseResult deleteBatch(@RequestBody List<Long> ids) {
        return tagsService.deleteBatch(ids);
    }
}

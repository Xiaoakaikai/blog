package com.bkk.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bkk.annotation.BusinessLogger;
import com.bkk.annotation.OperationLogger;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.dto.ArticleDTO;
import com.bkk.domain.vo.ArticleListVO;
import com.bkk.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/article")
@Api(tags = "文章管理")
public class ArticleController {

    @PostMapping("/list")
    @ApiOperation(value = "文章列表", httpMethod = "GET", response = ResponseResult.class, notes = "文章列表")
    public ResponseResult<Page<ArticleListVO>> list(@RequestBody Map<String, Object> map) {
        return articleService.list(map);
    }

    @Autowired
    private ArticleService articleService;

    @OperationLogger(value = "保存文章")
    @PostMapping("/add")
    @ApiOperation(value = "添加文章", httpMethod = "POST", response = ResponseResult.class, notes = "添加文章")
    public ResponseResult add(@RequestBody ArticleDTO articleDTO) {
        return articleService.add(articleDTO);
    }

    @OperationLogger(value = "删除文章")
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除文章", httpMethod = "DELETE", response = ResponseResult.class, notes = "删除文章")
    public ResponseResult delete(@PathVariable Long id) {
        return articleService.delete(id);
    }

    @OperationLogger(value = "批量删除文章")
    @DeleteMapping("/deleteBatch")
    @ApiOperation(value = "批量删除文章", httpMethod = "DELETE", response = ResponseResult.class, notes = "批量删除文章")
    public ResponseResult deleteBatch(@RequestBody List<Long> ids) {
        return articleService.deleteBatch(ids);
    }

    @GetMapping("/info")
    @ApiOperation(value = "文章详情", httpMethod = "GET", response = ResponseResult.class, notes = "文章详情")
    public ResponseResult<ArticleDTO> info(Long id) {
        return articleService.info(id);
    }

    @OperationLogger(value = "修改文章")
    @PutMapping("/update")
    @ApiOperation(value = "修改文章", httpMethod = "PUT", response = ResponseResult.class, notes = "修改文章")
    public ResponseResult update(@RequestBody ArticleDTO articleDTO) {
        return articleService.update(articleDTO);
    }

    @OperationLogger(value = "置顶文章")
    @PostMapping("/top")
    @ApiOperation(value = "置顶文章", httpMethod = "POST", response = ResponseResult.class, notes = "置顶文章")
    public ResponseResult top(@RequestBody ArticleDTO articleDTO) {
        return articleService.top(articleDTO);
    }

    @OperationLogger(value = "发布/下架 文章")
    @PostMapping("/pubOrShelf")
    @ApiOperation(value = "发布/下架 文章", httpMethod = "POST", response = ResponseResult.class, notes = "发布/下架 文章")
    public ResponseResult pubOrShelf(@RequestBody ArticleDTO articleDTO) {
        return articleService.pubOrShelf(articleDTO);
    }


}

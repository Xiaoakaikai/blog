package com.bkk.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bkk.annotation.BusinessLogger;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.vo.ArticleInfoVO;
import com.bkk.domain.vo.ArticlePreviewVO;
import com.bkk.domain.vo.ArticleSearchVO;
import com.bkk.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
@Api(tags = "前台文章管理")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @BusinessLogger(value = "首页-用户访问首页",type = "查询",desc = "查询所有文章")
    @GetMapping("/list")
    @ApiOperation(value = "文章列表", httpMethod = "GET", response = ResponseResult.class, notes = "文章列表")
    public ResponseResult<Page<ArticlePreviewVO>> list(Integer pageNo, Integer pageSize,
                                                       @RequestParam(value = "categoryId", required = false) Long categoryId,
                                                       @RequestParam(value = "tagId", required = false) Long tagId) {
        return articleService.list(pageNo, pageSize, categoryId, tagId);
    }

    @BusinessLogger(value = "首页-查看文章详情",type = "查询",desc = "查看文章详情")
    @GetMapping("/{id}")
    @ApiOperation(value = "文章详情", httpMethod = "GET", response = ResponseResult.class, notes = "文章详情")
    public ResponseResult<ArticleInfoVO> getArticle(@PathVariable Long id) {
        return articleService.getArticle(id);
    }

    @BusinessLogger(value = "首页-归档",type = "查询",desc = "归档")
    @GetMapping("/archive")
    @ApiOperation(value = "归档列表", httpMethod = "GET", response = ResponseResult.class, notes = "归档列表")
    public ResponseResult<Page<ArticlePreviewVO>> archive(Integer pageNo, Integer pageSize) {
        return articleService.archive(pageNo, pageSize);
    }

    @BusinessLogger(value = "文章-文章点赞",type = "查询",desc = "文章点赞")
    @GetMapping("/articleLike")
    @ApiOperation(value = "文章点赞", httpMethod = "GET", response = ResponseResult.class, notes = "文章点赞")
    public ResponseResult articleLike(@RequestParam Long articleId) {
        return articleService.articleLike(articleId);
    }

    @BusinessLogger(value = "首页-搜索文章",type = "查询",desc = "搜索文章")
    @GetMapping("/searchArticle")
    @ApiOperation(value = "搜索文章", httpMethod = "GET", response = ResponseResult.class, notes = "搜索文章")
    public ResponseResult<List<ArticleSearchVO>> searchArticle(@RequestParam String keywords) {
        return articleService.searchArticle(keywords);
    }
}

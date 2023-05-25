package com.bkk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.dto.ArticleDTO;
import com.bkk.domain.entity.Article;

import java.util.List;
import java.util.Map;


/**
 * 博客文章表(Article)表服务接口
 *
 * @author makejava
 * @since 2023-03-26 22:06:03
 */
public interface ArticleService extends IService<Article> {

    ResponseResult list(Integer pageNo, Integer pageSize, Long categoryId, Long tagId);

    ResponseResult getArticle(Long id);

    ResponseResult archive(Integer pageNo, Integer pageSize);

    ResponseResult articleLike(Long articleId);

    ResponseResult searchArticle(String keywords);

    ResponseResult list(Map<String, Object> map);

    ResponseResult add(ArticleDTO articleDTO);

    ResponseResult delete(Long id);

    ResponseResult info(Long id);

    ResponseResult update(ArticleDTO articleDTO);

    ResponseResult top(ArticleDTO articleDTO);

    ResponseResult pubOrShelf(ArticleDTO articleDTO);

    ResponseResult deleteBatch(List<Long> ids);

}


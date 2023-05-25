package com.bkk.strategy;

import com.bkk.domain.vo.ArticleSearchVO;

import java.util.List;

public interface SearchStrategy {
    /**
     * 搜索文章
     *
     * @param keywords 关键字
     * @return {@link List< ArticleSearchVO >} 文章列表
     */
    List<ArticleSearchVO> searchArticle(String keywords);
}
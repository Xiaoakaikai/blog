package com.bkk.strategy.context;

import com.bkk.domain.vo.ArticleSearchVO;
import com.bkk.strategy.SearchStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * @apiNote 搜索策略上下文
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SearchStrategyContext {

    private final Map<String, SearchStrategy> searchStrategyMap;

    /**
     * 执行搜索策略
     *
     * @param keywords 关键字
     * @return {@link List< ArticleSearchVO >} 搜索文章
     */
    public List<ArticleSearchVO> executeSearchStrategy(String searchMode, String keywords) {
        return searchStrategyMap.get(searchMode).searchArticle(keywords);
    }

}
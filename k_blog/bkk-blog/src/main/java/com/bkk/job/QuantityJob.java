package com.bkk.job;

import com.bkk.domain.entity.Article;
import com.bkk.service.ArticleService;
import com.bkk.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.bkk.constants.RedisConstants.ARTICLE_READING;

/**
 * 定时任务，更新阅读量
 */
@Component
public class QuantityJob {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ArticleService articleService;

    @Scheduled(cron = "0 0 5 * * ?")
    public void updateViewCount(){
        //获取redis中的浏览量
        Map<String, Integer> viewCountMap = redisCache.getCacheMap(ARTICLE_READING);

        List<Article> articles = viewCountMap.entrySet()
                .stream()
                .map(entry -> Article.builder().id(Long.valueOf(entry.getKey())).quantity(entry.getValue().intValue()).build())
                .collect(Collectors.toList());
        //更新到数据库中
        articleService.updateBatchById(articles);

    }
}

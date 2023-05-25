package com.bkk.runner;

import com.bkk.domain.entity.Article;
import com.bkk.mapper.ArticleMapper;
import com.bkk.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.bkk.constants.RedisConstants.ARTICLE_READING;

@Component
public class QuantityRunner implements CommandLineRunner {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private RedisCache redisCache;

    @Override
    public void run(String... args) throws Exception {
        //查询博客信息  id  viewCount
        List<Article> articles = articleMapper.selectList(null);
        Map<String, Integer> viewCountMap = articles.stream()
                .collect(Collectors.toMap(article -> article.getId().toString(), article -> {
                    return article.getQuantity().intValue();//
                }));
        //存储到redis中
        redisCache.setCacheMap(ARTICLE_READING, viewCountMap);

    }

}

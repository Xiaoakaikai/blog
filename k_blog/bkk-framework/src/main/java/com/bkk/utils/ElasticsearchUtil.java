package com.bkk.utils;

import com.alibaba.fastjson.JSON;
import com.bkk.domain.vo.ArticleSearchVO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.data.elasticsearch.core.query.UpdateResponse;
import org.springframework.stereotype.Component;

/**
 * @apiNote Elasticsearch工具类
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ElasticsearchUtil {

    private static final Logger logger = LoggerFactory.getLogger(ElasticsearchUtil.class);

    private final ElasticsearchRestTemplate elasticsearchRestTemplate;
    /**
     * 新增数据
     * @param articleSearchVO 数据对象
     */
    public void save(ArticleSearchVO articleSearchVO) {
        long time = System.currentTimeMillis();
        elasticsearchRestTemplate.save(articleSearchVO);
        logger.info("耗时:"+(System.currentTimeMillis() - time));
    }

    /**
     * 删除数据
     * @param id 文章ID
     */
    public void delete(Long id) {
        elasticsearchRestTemplate.delete(id.toString(), ArticleSearchVO.class);
    }

    /**
     * 修改数据
     * @param articleSearchVO 数据对象
     */
    public void update(ArticleSearchVO articleSearchVO) {
        String obj = JSON.toJSONString(articleSearchVO);
        Document document = Document.parse(obj);

        UpdateQuery query = UpdateQuery
                .builder(String.valueOf(articleSearchVO.getId()))
                .withDocument(document)
                .build();

        IndexCoordinates indexCoordinates = elasticsearchRestTemplate.getIndexCoordinatesFor(ArticleSearchVO.class);

        UpdateResponse update = elasticsearchRestTemplate.update(query, indexCoordinates);
    }
}

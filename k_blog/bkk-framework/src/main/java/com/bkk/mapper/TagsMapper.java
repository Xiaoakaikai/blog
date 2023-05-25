package com.bkk.mapper;
import com.bkk.domain.entity.Tags;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * 博客标签表(Tags)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-27 16:54:59
 */
@Mapper
public interface TagsMapper extends BaseMapper<Tags> {

    @MapKey("id")
    List<Map<String, Object>> countTags();

    void saveArticleTags(@Param("articleId") Long articleId, @Param("tags")List<Long> tags);

    List<String> selectByArticleId(Long articleId);

    void deleteByArticleIds(@Param("ids") List<Long> ids);

    Integer articleCountByTagsId(Long tagsId);

}

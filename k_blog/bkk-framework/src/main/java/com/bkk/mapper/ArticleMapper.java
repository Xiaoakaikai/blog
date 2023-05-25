package com.bkk.mapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bkk.domain.dto.ArticleDTO;
import com.bkk.domain.entity.Article;
import com.bkk.domain.vo.ContributeVO;
import com.bkk.domain.vo.LatestArticleVO;
import com.bkk.domain.vo.TagVO;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 博客文章表(Article)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-26 22:06:02
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    List<TagVO> getTags(@Param("id") Long id);

    List<LatestArticleVO> getNewArticles(@Param("id") Long id, @Param("isPublish") Integer isPublish);

    LatestArticleVO getNextOrLastArticle(@Param("id") Long id, @Param("type") Integer type, @Param("isPublish") Integer isPublish);

    List<LatestArticleVO> listRecommendArticles(@Param("articleId") Long articleId);

    /**
     * 文章贡献度
     * @param lastTime 开始时间
     * @param nowTime 结束时间
     * @return
     */
    List<ContributeVO> contribute(@Param("lastTime") String lastTime, @Param("nowTime")String nowTime);

    /**
     * 后台根据主键获取文章详情
     * @param id 主键id
     * @return
     */
    ArticleDTO selectPrimaryKey(Long id);

}

package com.bkk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bkk.constants.RedisConstants;
import com.bkk.constants.SystemConstants;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.entity.Article;
import com.bkk.domain.entity.Page;
import com.bkk.domain.entity.WebConfig;
import com.bkk.mapper.WebConfigMapper;
import com.bkk.service.*;
import com.bkk.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.bkk.constants.SqlConf.LIMIT_ONE;

/**
 * 网站配置表(WebConfig)表服务实现类
 *
 * @author makejava
 * @since 2023-03-30 16:25:43
 */
@Service("webConfigService")
public class WebConfigServiceImpl extends ServiceImpl<WebConfigMapper, WebConfig> implements WebConfigService {

    @Autowired
    private PageService pageService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagsService tagsService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RedisCache redisCache;

    /**
     * 获取网站信息
     * @return
     */
    @Override
    public ResponseResult webSiteInfo() {
        //网站信息
        WebConfig webConfig = getOne(new LambdaQueryWrapper<WebConfig>()
                .select(WebConfig::getAuthorAvatar,WebConfig::getIsMusicPlayer,WebConfig::getAuthorInfo,WebConfig::getTouristAvatar,WebConfig::getBulletin,
                        WebConfig::getQqNumber,WebConfig::getGitee,WebConfig::getGithub,WebConfig::getLogo,
                        WebConfig::getAboutMe,WebConfig::getEmail,WebConfig::getShowList,WebConfig::getLoginTypeList,
                        WebConfig::getRecordNum,WebConfig::getAuthor,WebConfig::getAliPay,WebConfig::getWeixinPay,
                        WebConfig::getWebUrl, WebConfig::getSummary,WebConfig::getName,WebConfig::getKeyword)
                .last(LIMIT_ONE));

        //文章分类标签
        Integer articleCount = articleService.count(new LambdaQueryWrapper<Article>().eq(Article::getIsPublish, SystemConstants.ARTICLE_STATUS_NORMAL));
        Integer tagCount = tagsService.count();
        Integer categoryCount = categoryService.count();
        // 查询访问量
        String viewsCount = getViewsCount().toString();
        Map<String,Object> map = new HashMap<>();
        map.put("articleCount",articleCount);
        map.put("categoryCount",categoryCount);
        map.put("tagCount",tagCount);
        map.put("viewsCount",viewsCount);

        //查询页面信息
        List<Page> pageList = pageService.list(new LambdaQueryWrapper<Page>()
                .select(Page::getPageCover, Page::getPageLabel));

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("webSite", webConfig);
        resultMap.put("pageList", pageList);
        resultMap.put("count", map);
        return ResponseResult.okResult(resultMap);
    }

    /**
     * 网站配置
     * @return
     */
    @Override
    public ResponseResult webConfigList() {
        WebConfig webConfig = baseMapper.selectOne(new LambdaQueryWrapper<WebConfig>().last(LIMIT_ONE));
        return ResponseResult.okResult(webConfig);
    }

    /**
     * 修改网站配置
     * @param webConfig
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult update(WebConfig webConfig) {
        int update = baseMapper.updateById(webConfig);
        return update > 0 ? ResponseResult.okResult() : ResponseResult.errorResult("修改失败！");
    }

    /**
     * 获取网站访问量
     * @return
     */
    private Object getViewsCount() {
        Object count = redisCache.getCacheObject(RedisConstants.BLOG_VIEWS_COUNT);
        Object viewsCount = Optional.ofNullable(count).orElse(0);
        return viewsCount;
    }
}


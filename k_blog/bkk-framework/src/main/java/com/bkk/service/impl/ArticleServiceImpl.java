package com.bkk.service.impl;

import com.alibaba.excel.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bkk.constants.SqlConf;
import com.bkk.constants.SystemConstants;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.dto.ArticleDTO;
import com.bkk.domain.entity.Article;
import com.bkk.domain.entity.Category;
import com.bkk.domain.entity.Tags;
import com.bkk.domain.vo.*;
import com.bkk.exception.SystemException;
import com.bkk.mapper.ArticleMapper;
import com.bkk.mapper.CategoryMapper;
import com.bkk.mapper.TagsMapper;
import com.bkk.service.ArticleService;
import com.bkk.service.CategoryService;
import com.bkk.service.TagsService;
import com.bkk.strategy.context.SearchStrategyContext;
import com.bkk.utils.BaseContext;
import com.bkk.utils.BeanCopyUtils;
import com.bkk.utils.RedisCache;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.bkk.constants.RedisConstants.*;
import static com.bkk.enums.AppHttpCodeEnum.ARTICLE_NOT_EXIST;
import static com.bkk.enums.AppHttpCodeEnum.PARAMS_ILLEGAL;
import static com.bkk.enums.SearchModelEnum.ELASTICSEARCH;
import static com.bkk.enums.SearchModelEnum.MYSQL;

/**
 * 博客文章表(Article)表服务实现类
 *
 * @author makejava
 * @since 2023-03-26 22:06:03
 */
@Service("articleService")
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private TagsService tagsService;

    @Autowired
    private TagsMapper tagsMapper;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private SearchStrategyContext searchStrategyContext;

    /**
     * 查询文章分页列表
     * @param pageNo
     * @param pageSize
     * @param categoryId
     * @param tagId
     * @return
     */
    @Override
    public ResponseResult list(Integer pageNo, Integer pageSize, Long categoryId, Long tagId) {
        //查询条件
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        // 如果 有categoryId 就要 查询时要和传入的相同
        queryWrapper.eq(categoryId != null, Article::getCategoryId, categoryId);
        // 状态是正式发布的
        queryWrapper.eq(Article::getIsPublish, SystemConstants.ARTICLE_STATUS_NORMAL);
        // 对isTop进行降序
        queryWrapper.orderByDesc(Article::getIsStick);
        //分页查询
        Page<Article> page = new Page<>(pageNo, pageSize);
        page(page, queryWrapper);

        List<Article> articleList = page.getRecords();
        //查询categoryName
        articleList.stream()
                .map(article -> article.setCategoryName(categoryService.getById(article.getCategoryId()).getName()))
                .collect(Collectors.toList());
        //封装查询结果
        List<ArticlePreviewVO> articlePreviewVOS = BeanCopyUtils.copyBeanList(articleList, ArticlePreviewVO.class);
        //查询tags
        articlePreviewVOS.stream()
                        .map(articlePreviewVO -> articlePreviewVO.setTagVOList(baseMapper.getTags(articlePreviewVO.getId())))
                                .collect(Collectors.toList());
        if (categoryId != null) {
            Map<String, Object> map = new HashMap<>();
            Category category = categoryService.getById(categoryId);
            if (category != null) {
                String name = category.getName();
                map.put(SqlConf.NAME,name);
            }
            map.put(SystemConstants.RECORDS, articlePreviewVOS);
            return ResponseResult.okResult(map);
        } else if (tagId != null) {
            List<ArticlePreviewVO> previewVOList = articlePreviewVOS.stream()
                    .filter(new Predicate<ArticlePreviewVO>() {
                        @Override
                        public boolean test(ArticlePreviewVO articlePreviewVO) {
                            List<TagVO> tagVOList = articlePreviewVO.getTagVOList();
                            for (TagVO tagVO : tagVOList) {
                                if (tagVO.getId().equals(tagId)) {
                                    return true;
                                }
                            }
                            return false;
                        }
                    })
                    .collect(Collectors.toList());
            Map<String, Object> map = new HashMap<>();
            Tags tags = tagsService.getById(tagId);
            if (tags != null) {
                String name = tags.getName();
                map.put(SqlConf.NAME,name);
            }
            map.put(SystemConstants.RECORDS, previewVOList);
            return ResponseResult.okResult(map);
        } else {
            Page<ArticlePreviewVO> previewVOPage = new Page<>();
            BeanUtils.copyProperties(page, previewVOPage, "records");
            previewVOPage.setRecords(articlePreviewVOS);

            return ResponseResult.okResult(previewVOPage);
        }

    }

    /**
     * 查看文章详情
     * @param id
     * @return
     */
    @Override
    public ResponseResult getArticle(Long id) {
        Article article = getById(id);
        ArticleInfoVO articleInfoVO = BeanCopyUtils.copyBean(article, ArticleInfoVO.class);
        //分类
        Category category = categoryService.getById(article.getCategoryId());
        if (Objects.nonNull(category)) {
            articleInfoVO.setCategory(category);
        }
        //标签
        List<TagVO> tagVOS = baseMapper.getTags(id);
        articleInfoVO.setTagList(tagVOS);
        //最新文章
        List<LatestArticleVO> newArticles = baseMapper.getNewArticles(id, SystemConstants.ARTICLE_STATUS_NORMAL);
        articleInfoVO.setNewestArticleList(newArticles);
        // 查询上一篇下一篇文章
        LatestArticleVO lastArticle = baseMapper.getNextOrLastArticle(id, SystemConstants.LAST_ARTICLE, SystemConstants.ARTICLE_STATUS_NORMAL);
        articleInfoVO.setLastArticle(lastArticle);
        LatestArticleVO nextArticle = baseMapper.getNextOrLastArticle(id, SystemConstants.NEXT_ARTICLE, SystemConstants.ARTICLE_STATUS_NORMAL);
        articleInfoVO.setNextArticle(nextArticle);

        //相关推荐
        List<LatestArticleVO> recommendArticles = baseMapper.listRecommendArticles(id);
        articleInfoVO.setRecommendArticleList(recommendArticles);

        // 封装点赞量和浏览量
        Integer likeCount = redisCache.getCacheMapValue(ARTICLE_LIKE_COUNT, id.toString());
        articleInfoVO.setLikeCount(likeCount);
        Integer quantity = redisCache.getCacheMapValue(ARTICLE_READING, id.toString());
        articleInfoVO.setQuantity(quantity);

        //增加阅读量
        redisCache.incrementCacheMapValue(ARTICLE_READING, id.toString(), 1);

        return ResponseResult.okResult(articleInfoVO);
    }

    /**
     * 文章归档
     * @return
     */
    @Override
    public ResponseResult archive(Integer pageNo, Integer pageSize) {
        //查询条件
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        // 状态是正式发布的
        queryWrapper.eq(Article::getIsPublish, SystemConstants.ARTICLE_STATUS_NORMAL);
        //分页查询
        Page<Article> page = new Page<>(pageNo, pageSize);
        page(page, queryWrapper);

        List<Article> articleList = page.getRecords();
        //查询categoryName
        articleList.stream()
                .map(article -> article.setCategoryName(categoryService.getById(article.getCategoryId()).getName()))
                .collect(Collectors.toList());
        //封装查询结果
        List<ArticlePreviewVO> articlePreviewVOS = BeanCopyUtils.copyBeanList(articleList, ArticlePreviewVO.class);
        Page<ArticlePreviewVO> previewVOPage = new Page<>();
        BeanUtils.copyProperties(page, previewVOPage, "records");
        previewVOPage.setRecords(articlePreviewVOS);

        return ResponseResult.okResult(previewVOPage);
    }

    /**
     *文章点赞
     * @param articleId
     * @return
     */
    @Override
    public ResponseResult articleLike(Long articleId) {
        // 判断是否点赞
        String articleLikeKey = ARTICLE_USER_LIKE + BaseContext.getCurrentId();
        if (redisCache.sIsMember(articleLikeKey, articleId)) {
            // 点过赞则删除文章id
            redisCache.sRemove(articleLikeKey, articleId);
            // 文章点赞量-1
            redisCache.hDecr(ARTICLE_LIKE_COUNT, articleId.toString(), 1L);
        } else {
            // 未点赞则增加文章id
            redisCache.sAdd(articleLikeKey, articleId);
            // 文章点赞量+1
            redisCache.incrementCacheMapValue(ARTICLE_LIKE_COUNT, articleId.toString(), 1L);
        }
        return ResponseResult.okResult();
    }

    /**
     * 根据关键字查询文章
     * @param keywords
     * @return
     */
    @Override
    public ResponseResult searchArticle(String keywords) {
        if (StringUtils.isBlank(keywords)) {
            throw new SystemException(PARAMS_ILLEGAL);
        }
        //搜索逻辑
        List<ArticleSearchVO> articleSearchVOS = searchStrategyContext.executeSearchStrategy(MYSQL.getStrategy(), keywords);
        return ResponseResult.okResult(articleSearchVOS);
    }

    /**
     * 后台获取文章列表
     * @param map
     * @return
     */
    @Override
    public ResponseResult list(Map<String, Object> map) {
        //添加条件
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //判断是否有分类id
        String categoryIdString = (String) map.get("categoryId");
        Long categoryId = null;
        if (Objects.nonNull(categoryIdString) && !categoryIdString.equals("")) {
            categoryId = Long.parseLong(categoryIdString);
        }
        queryWrapper.eq(Objects.nonNull(categoryId), Article::getCategoryId, categoryId);
        //判断是否有标签id
        String tagIdString = (String) map.get("tagId");
        Long tagId = Objects.nonNull(tagIdString) && !tagIdString.equals("") ? Long.parseLong(tagIdString) : null;

        Object isPublishTmp = map.get("isPublish");
        String isPublishString = String.valueOf(Objects.nonNull(isPublishTmp) ? isPublishTmp : "");
        Integer isPublish = Objects.nonNull(isPublishString) && !isPublishString.equals("") ? Integer.parseInt(isPublishString) : null;
        queryWrapper.eq(Objects.nonNull(isPublish), Article::getIsPublish, isPublish);

        String title = (String) map.get("title");
        queryWrapper.like(Objects.nonNull(title) && !title.equals(""), Article::getTitle, title);

        Integer pageNo = (Integer) map.get("pageNo");
        Integer pageSize = (Integer) map.get("pageSize");
        Page<Article> page = new Page<>(pageNo, pageSize);
        page(page, queryWrapper);
        List<Article> records = page.getRecords();
        List<ArticleListVO> articleListVOS = records.stream()
                .map(new Function<Article, ArticleListVO>() {
                    @Override
                    public ArticleListVO apply(Article article) {
                        ArticleListVO articleListVO = BeanCopyUtils.copyBean(article, ArticleListVO.class);
                        Category category = categoryService.getById(article.getCategoryId());
                        articleListVO.setCategoryName(category.getName());
                        List<TagVO> tags = baseMapper.getTags(article.getId());
                        List<String> tagNames = tags.stream().map(tagVO -> tagVO.getName()).collect(Collectors.toList());
                        String tagName = tagNames.stream().collect(Collectors.joining(","));
                        articleListVO.setTagNames(tagName);
                        return articleListVO;
                    }
                }).collect(Collectors.toList());
        articleListVOS = articleListVOS.stream()
                .filter(new Predicate<ArticleListVO>() {
                    @Override
                    public boolean test(ArticleListVO articleListVO) {
                        List<String> tagNames = Arrays.asList(articleListVO.getTagNames().split(","));
                        if (Objects.nonNull(tagId)) {
                            Tags tag = tagsService.getById(tagId);
                            if (Objects.nonNull(tag) && !tagNames.contains(tag.getName())) {
                                return false;
                            }
                        }
                        return true;
                    }
                }).collect(Collectors.toList());
        Page<ArticleListVO> listVOPage = new Page<>();
        BeanUtils.copyProperties(page, listVOPage, "records");
        listVOPage.setRecords(articleListVOS);
        return ResponseResult.okResult(listVOPage);
    }

    /**
     * 新增文章
     * @param articleDTO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult add(ArticleDTO articleDTO) {
        Article article = BeanCopyUtils.copyBean(articleDTO, Article.class);
        article.setUserId(BaseContext.getCurrentId());
        //添加分类
        Long categoryId = savaCategory(articleDTO.getCategoryName());
        //添加标签
        List<Long> tagsIdList = saveTagsList(articleDTO);

        article.setCategoryId(categoryId);

        int insert = baseMapper.insert(article);
        if (insert > 0) {
            tagsMapper.saveArticleTags(article.getId(), tagsIdList);
        }

        return ResponseResult.okResult();
    }

    /**
     * 删除文章
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult delete(Long id) {
        int delete = baseMapper.deleteById(id);
        if (delete > 0) {
            this.deleteAfter(Collections.singletonList(id));
        }
        return ResponseResult.okResult();
    }

    /**
     * 后台根据主键获取文章详情
     * @param id 主键id
     * @return
     */
    @Override
    public ResponseResult info(Long id) {
        ArticleDTO articleDTO = baseMapper.selectPrimaryKey(id);
        articleDTO.setTags(tagsMapper.selectByArticleId(id));
        return ResponseResult.okResult(articleDTO);
    }

    /**
     * 修改文章
     * @param articleDTO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult update(ArticleDTO articleDTO) {
        Article article = baseMapper.selectById(articleDTO.getId());
        if (Objects.isNull(article)) {
            throw new SystemException(ARTICLE_NOT_EXIST);
        }
        //添加分类
        Long categoryId = savaCategory(articleDTO.getCategoryName());
        //添加标签
        List<Long> tagsIdList = saveTagsList(articleDTO);

        article = BeanCopyUtils.copyBean(articleDTO, Article.class);
        article.setCategoryId(categoryId);
        article.setUserId(BaseContext.getCurrentId());
        baseMapper.updateById(article);
        //先删出所有标签
        tagsMapper.deleteByArticleIds(Collections.singletonList(article.getId()));
        //然后新增标签
        tagsMapper.saveArticleTags(article.getId(), tagsIdList);
        return ResponseResult.okResult();
    }

    /**
     * 置顶文章
     * @param articleDTO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult top(ArticleDTO articleDTO) {
        LambdaUpdateWrapper<Article> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(Objects.nonNull(articleDTO.getIsStick()), Article::getIsStick, articleDTO.getIsStick());
        updateWrapper.eq(Objects.nonNull(articleDTO.getId()), Article::getId, articleDTO.getId());
        update(updateWrapper);
        return ResponseResult.okResult();
    }

    /**
     * 发布/下架文章
     * @param articleDTO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult pubOrShelf(ArticleDTO articleDTO) {
        LambdaUpdateWrapper<Article> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(Objects.nonNull(articleDTO.getIsPublish()), Article::getIsPublish, articleDTO.getIsPublish());
        updateWrapper.eq(Objects.nonNull(articleDTO.getId()), Article::getId, articleDTO.getId());
        update(updateWrapper);
        return ResponseResult.okResult();
    }

    /**
     * 批量删除文章
     * @param ids
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult deleteBatch(List<Long> ids) {
        int i = baseMapper.deleteBatchIds(ids);
        if (i > 0) {
            this.deleteAfter(ids);
        }
        return ResponseResult.okResult();
    }


    //-------------------------自定义方法开始--------------------------
    /**
     * 如果分类不存在则新增
     * @param categoryName
     * @return
     */
    private Long savaCategory(String categoryName) {
        Category category = categoryMapper.selectOne(new QueryWrapper<Category>().eq(SqlConf.NAME, categoryName));
        if (category == null){
            category = Category.builder().name(categoryName).sort(0).build();
            categoryMapper.insert(category);
        }
        return category.getId();
    }

    /**
     * 将数据库不存在的标签新增
     * @param article
     * @return
     */
    private List<Long> saveTagsList(ArticleDTO article) {
        List<Long> tagList = new ArrayList<>();
        article.getTags().forEach(item ->{
            Tags tags = tagsMapper.selectOne(new QueryWrapper<Tags>().eq(SqlConf.NAME, item));
            if (tags == null){
                tags = Tags.builder().name(item).sort(0).build();
                tagsMapper.insert(tags);
            }
            tagList.add(tags.getId());
        });
        return tagList;
    }

    /**
     * 删除文章后的一些同步删除
     * @param ids
     */
    private void deleteAfter(List<Long> ids){
        tagsMapper.deleteByArticleIds(ids);
//        threadPoolTaskExecutor.execute(()->this.deleteEsData(ids));
    }
}


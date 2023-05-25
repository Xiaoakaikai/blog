package com.bkk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bkk.constants.SqlConf;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.entity.Article;
import com.bkk.domain.entity.Tags;
import com.bkk.domain.vo.TagVO;
import com.bkk.exception.SystemException;
import com.bkk.mapper.TagsMapper;
import com.bkk.service.ArticleService;
import com.bkk.service.TagsService;
import com.bkk.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.bkk.constants.SqlConf.LIMIT_ONE;
import static com.bkk.enums.AppHttpCodeEnum.*;

/**
 * 博客标签表(Tags)表服务实现类
 *
 * @author makejava
 * @since 2023-03-27 16:55:00
 */
@Service("tagsService")
public class TagsServiceImpl extends ServiceImpl<TagsMapper, Tags> implements TagsService {

    @Autowired
    private ArticleService articleService;

    /**
     * 获取标签列表
     * @return
     */
    @Override
    public ResponseResult getAllTags() {
        //添加条件
        LambdaQueryWrapper<Tags> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Tags::getSort);
        List<Tags> tagsList = list(queryWrapper);
        List<TagVO> tagVOS = BeanCopyUtils.copyBeanList(tagsList, TagVO.class);
        return ResponseResult.okResult(tagVOS);
    }

    /**
     * 后台获取标签列表
     * @param pageNo
     * @param pageSize
     * @param name
     * @return
     */
    @Override
    public ResponseResult list(Integer pageNo, Integer pageSize, String name) {
        //添加条件
        LambdaQueryWrapper<Tags> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Objects.nonNull(name), Tags::getName, name);
        queryWrapper.orderByDesc(Tags::getSort);
        Page<Tags> page = new Page<>(pageNo, pageSize);
        page(page, queryWrapper);

        List<Tags> records = page.getRecords();
        records = records.stream()
                .map(tags -> tags.setArticleCount(baseMapper.articleCountByTagsId(tags.getId())))
                .collect(Collectors.toList());
        page.setRecords(records);
        return ResponseResult.okResult(page);
    }

    /**
     * 新增标签
     * @param tags
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult add(Tags tags) {
        validateName(tags.getName());
        int insert = baseMapper.insert(tags);
        return insert > 0 ? ResponseResult.okResult() : ResponseResult.errorResult("添加标签失败！");
    }

    /**
     * 根据id获取标签信息
     * @param id
     * @return
     */
    @Override
    public ResponseResult info(Long id) {
        Tags tags = baseMapper.selectById(id);
        return ResponseResult.okResult(tags);
    }

    /**
     * 修改标签
     * @param tags
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult update(Tags tags) {
        Tags one = baseMapper.selectById(tags);
        if (!one.getName().equals(tags.getName())) validateName(tags.getName());
        int update = baseMapper.updateById(tags);
        return update > 0 ? ResponseResult.okResult() : ResponseResult.errorResult("修改失败！");
    }

    /**
     * 置顶标签
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult top(Long id) {
        Tags one = baseMapper.selectOne(new LambdaQueryWrapper<Tags>()
                .last(LIMIT_ONE).orderByDesc(Tags::getSort));
        Tags tags = baseMapper.selectById(id);
        if (tags.getSort().equals(one.getSort())) throw new SystemException(DATA_TAG_IS_TOP);
        tags.setSort(one.getSort() + 1);
        baseMapper.updateById(tags);
        return ResponseResult.okResult();
    }

    /**
     * 删除标签
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult remove(Long id) {
        baseMapper.deleteById(id);
        return ResponseResult.okResult();
    }

    /**
     * 批量删除标签
     * @param ids
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult deleteBatch(List<Long> ids) {
        baseMapper.deleteBatchIds(ids);
        return ResponseResult.okResult();
    }


    //-----------自定义方法开始------------
    public void validateName(String name){
        if (Objects.isNull(name)) {
            throw new SystemException(PARAMS_ILLEGAL);
        }
        Tags one = baseMapper.selectOne(new LambdaQueryWrapper<Tags>()
                .eq(Tags::getName, name));
        if (Objects.nonNull(one)) {
            throw new SystemException(DATA_TAG_IS_EXIST);
        }
    }

}


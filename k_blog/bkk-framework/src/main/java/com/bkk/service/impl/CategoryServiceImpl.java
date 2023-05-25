package com.bkk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.entity.Article;
import com.bkk.domain.entity.Category;
import com.bkk.domain.entity.Tags;
import com.bkk.domain.vo.CategoryVO;
import com.bkk.exception.SystemException;
import com.bkk.mapper.CategoryMapper;
import com.bkk.service.ArticleService;
import com.bkk.service.CategoryService;
import com.bkk.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.bkk.constants.SqlConf.LIMIT_ONE;
import static com.bkk.enums.AppHttpCodeEnum.*;

/**
 * 博客分类表(Category)表服务实现类
 *
 * @author makejava
 * @since 2023-03-27 15:43:03
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private ArticleService articleService;

    /**
     * 获取分类列表
     * @return
     */
    @Override
    public ResponseResult categoryList() {
        //添加条件
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Category::getSort);
        List<Category> categoryList = list(queryWrapper);
        List<CategoryVO> categoryVOS = BeanCopyUtils.copyBeanList(categoryList, CategoryVO.class);

        List<CategoryVO> categoryVOList = categoryVOS.stream()
                .map(categoryVO -> categoryVO.setArticleNum(articleService.count(new LambdaQueryWrapper<Article>().eq(Article::getCategoryId, categoryVO.getId()))))
                .collect(Collectors.toList());

        return ResponseResult.okResult(categoryVOList);
    }

    /**
     * 后台获取分类列表
     * @param pageNo
     * @param pageSize
     * @param name
     * @return
     */
    @Override
    public ResponseResult list(Integer pageNo, Integer pageSize, String name) {
        //添加条件
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Objects.nonNull(name), Category::getName, name);
        queryWrapper.orderByDesc(Category::getSort);
        Page<Category> page = new Page<>(pageNo, pageSize);
        page(page, queryWrapper);

        List<Category> records = page.getRecords();
        records = records.stream()
                .map(new Function<Category, Category>() {
                    @Override
                    public Category apply(Category category) {
                        int count = articleService.count(new LambdaQueryWrapper<Article>()
                                .eq(Article::getCategoryId, category.getId()));
                        category.setArticleCount(count);
                        return category;
                    }
                }).collect(Collectors.toList());
        page.setRecords(records);
        return ResponseResult.okResult(page);
    }

    /**
     * 新增分类
     * @param category
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult add(Category category) {
        validateName(category.getName());
        int insert = baseMapper.insert(category);
        return insert > 0 ? ResponseResult.okResult() : ResponseResult.errorResult("添加分类失败！");
    }

    /**
     * 根据id获取分类信息
     * @param id
     * @return
     */
    @Override
    public ResponseResult info(Long id) {
        Category category = baseMapper.selectById(id);
        return ResponseResult.okResult(category);
    }

    /**
     * 修改分类信息
     * @param category
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult update(Category category) {
        Category one = baseMapper.selectById(category);
        if (!one.getName().equals(category.getName())) validateName(category.getName());
        int update = baseMapper.updateById(category);
        return update > 0 ? ResponseResult.okResult() : ResponseResult.errorResult("修改失败！");
    }

    /**
     * 置顶分类
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult top(Long id) {
        Category one = baseMapper.selectOne(new LambdaQueryWrapper<Category>()
                .last(LIMIT_ONE).orderByDesc(Category::getSort));
        Category category = baseMapper.selectById(id);
        if (category.getSort().equals(one.getSort())) throw new SystemException(CATEGORY_IS_TOP);
        category.setSort(one.getSort() + 1);
        int update = baseMapper.updateById(category);
        return update > 0 ? ResponseResult.okResult() : ResponseResult.errorResult("置顶失败！");
    }

    /**
     * 根据id删除分类
     * @param id
     * @return
     */
    @Override
    public ResponseResult delete(Long id) {
        int delete = baseMapper.deleteById(id);
        return delete > 0 ? ResponseResult.okResult() : ResponseResult.errorResult("删除失败！");
    }

    /**
     * 根据id批量删除分类
     * @param categoryList
     * @return
     */
    @Override
    public ResponseResult deleteBatch(List<Category> categoryList) {
        List<Long> ids = categoryList.stream().map(Category::getId).collect(Collectors.toList());
        int i = baseMapper.deleteBatchIds(ids);
        return i > 0 ? ResponseResult.okResult() : ResponseResult.errorResult("批量删除失败！");
    }


    //-----------自定义方法开始------------
    public void validateName(String name){
        if (Objects.isNull(name)) {
            throw new SystemException(PARAMS_ILLEGAL);
        }
        Category one = baseMapper.selectOne(new LambdaQueryWrapper<Category>()
                .eq(Category::getName, name));
        if (Objects.nonNull(one)) {
            throw new SystemException(CATEGORY_IS_EXIST);
        }
    }
}


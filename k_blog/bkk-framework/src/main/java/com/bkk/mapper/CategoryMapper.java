package com.bkk.mapper;
import com.bkk.domain.entity.Category;
import com.bkk.domain.vo.CategoryCountVO;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * 博客分类表(Category)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-27 15:43:02
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

    /**
     * 统计分类
     * @return
     */
    List<CategoryCountVO> countArticle();

}

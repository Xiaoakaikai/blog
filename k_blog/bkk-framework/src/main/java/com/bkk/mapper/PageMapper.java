package com.bkk.mapper;
import com.bkk.domain.entity.Page;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * (Page)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-30 16:45:39
 */
@Mapper
public interface PageMapper extends BaseMapper<Page> {

}

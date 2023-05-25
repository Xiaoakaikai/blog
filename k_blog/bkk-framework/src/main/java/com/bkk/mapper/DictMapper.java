package com.bkk.mapper;
import com.bkk.domain.entity.Dict;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * 字典表(Dict)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-11 20:39:35
 */
@Mapper
public interface DictMapper extends BaseMapper<Dict> {

}

package com.bkk.mapper;
import com.bkk.domain.entity.ExceptionLog;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * (ExceptionLog)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-14 15:36:59
 */
@Mapper
public interface ExceptionLogMapper extends BaseMapper<ExceptionLog> {

}

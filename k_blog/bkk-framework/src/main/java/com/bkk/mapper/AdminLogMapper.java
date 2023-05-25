package com.bkk.mapper;
import com.bkk.domain.entity.AdminLog;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * (AdminLog)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-14 15:36:58
 */
@Mapper
public interface AdminLogMapper extends BaseMapper<AdminLog> {

}

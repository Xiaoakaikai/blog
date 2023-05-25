package com.bkk.mapper;
import com.bkk.domain.entity.SystemConfig;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * 系统配置表(SystemConfig)表数据库访问层
 *
 * @author makejava
 * @since 2023-05-22 15:11:35
 */
@Mapper
public interface SystemConfigMapper extends BaseMapper<SystemConfig> {

}

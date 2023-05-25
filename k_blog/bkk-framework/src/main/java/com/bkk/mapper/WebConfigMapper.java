package com.bkk.mapper;
import com.bkk.domain.entity.WebConfig;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * 网站配置表(WebConfig)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-30 16:25:42
 */
@Mapper
public interface WebConfigMapper extends BaseMapper<WebConfig> {

}

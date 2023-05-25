package com.bkk.mapper;
import com.bkk.domain.entity.UserLog;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;


/**
 * 日志表(UserLog)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-14 15:36:59
 */
@Mapper
public interface UserLogMapper extends BaseMapper<UserLog> {

    List<Map<String,Object>> getUserAccess(String date);

}

package com.bkk.mapper;
import com.bkk.domain.entity.JobLog;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * 定时任务调度日志表(JobLog)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-14 15:36:59
 */
@Mapper
public interface JobLogMapper extends BaseMapper<JobLog> {

}

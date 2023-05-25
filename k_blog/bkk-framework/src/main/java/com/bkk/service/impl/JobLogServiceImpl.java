package com.bkk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bkk.domain.entity.JobLog;
import com.bkk.mapper.JobLogMapper;
import com.bkk.service.JobLogService;
import org.springframework.stereotype.Service;

/**
 * 定时任务调度日志表(JobLog)表服务实现类
 *
 * @author makejava
 * @since 2023-04-14 15:36:59
 */
@Service("jobLogService")
public class JobLogServiceImpl extends ServiceImpl<JobLogMapper, JobLog> implements JobLogService {

}


package com.bkk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.entity.ExceptionLog;
import com.bkk.mapper.ExceptionLogMapper;
import com.bkk.service.ExceptionLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * (ExceptionLog)表服务实现类
 *
 * @author makejava
 * @since 2023-04-14 15:36:59
 */
@Service("exceptionLogService")
public class ExceptionLogServiceImpl extends ServiceImpl<ExceptionLogMapper, ExceptionLog> implements ExceptionLogService {

    /**
     * 异常日志列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public ResponseResult list(Integer pageNo, Integer pageSize) {
        LambdaQueryWrapper<ExceptionLog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(ExceptionLog::getCreateTime);
        Page<ExceptionLog> page = new Page<>(pageNo, pageSize);
        page(page, queryWrapper);
        return ResponseResult.okResult(page);
    }

    /**
     * 删除异常日志
     * @param ids
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult delete(List<Long> ids) {
        int i = baseMapper.deleteBatchIds(ids);
        return i > 0 ? ResponseResult.okResult() : ResponseResult.errorResult("删除失败！");
    }
}


package com.bkk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.entity.AdminLog;
import com.bkk.mapper.AdminLogMapper;
import com.bkk.service.AdminLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * (AdminLog)表服务实现类
 *
 * @author makejava
 * @since 2023-04-14 15:36:59
 */
@Service("adminLogService")
public class AdminLogServiceImpl extends ServiceImpl<AdminLogMapper, AdminLog> implements AdminLogService {

    /**
     * 操作日志列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public ResponseResult list(Integer pageNo, Integer pageSize) {
        LambdaQueryWrapper<AdminLog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(AdminLog::getCreateTime);
        Page<AdminLog> page = new Page<>(pageNo, pageSize);
        page(page, queryWrapper);
        return ResponseResult.okResult(page);
    }

    /**
     * 删除操作日志
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


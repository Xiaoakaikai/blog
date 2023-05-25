package com.bkk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.entity.UserLog;
import com.bkk.mapper.UserLogMapper;
import com.bkk.service.UserLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 日志表(UserLog)表服务实现类
 *
 * @author makejava
 * @since 2023-04-14 15:36:59
 */
@Service("userLogService")
public class UserLogServiceImpl extends ServiceImpl<UserLogMapper, UserLog> implements UserLogService {

    /**
     * 用户日志列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public ResponseResult list(Integer pageNo, Integer pageSize) {
        LambdaQueryWrapper<UserLog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(UserLog::getCreateTime);
        Page<UserLog> page = new Page<>(pageNo, pageSize);
        page(page, queryWrapper);
        return ResponseResult.okResult(page);
    }

    /**
     * 删除用户日志
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


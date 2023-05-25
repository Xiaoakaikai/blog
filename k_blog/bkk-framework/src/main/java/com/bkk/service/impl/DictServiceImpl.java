package com.bkk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.entity.Dict;
import com.bkk.mapper.DictMapper;
import com.bkk.service.DictService;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 字典表(Dict)表服务实现类
 *
 * @author makejava
 * @since 2023-04-11 20:39:40
 */
@Service("dictService")
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    /**
     * 获取字典列表
     * @param pageNo
     * @param pageSize
     * @param name
     * @param isPublish
     * @return
     */
    @Override
    public ResponseResult list(Integer pageNo, Integer pageSize, String name, Integer isPublish) {
        LambdaQueryWrapper<Dict> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(name), Dict::getName, name)
                .eq(Objects.nonNull(isPublish), Dict::getIsPublish, isPublish);
        Page<Dict> page = new Page<>(pageNo, pageSize);
        page(page, queryWrapper);
        return ResponseResult.okResult(page);
    }
}


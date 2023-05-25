package com.bkk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.entity.Tags;
import com.bkk.domain.vo.TagVO;

import java.util.List;


/**
 * 博客标签表(Tags)表服务接口
 *
 * @author makejava
 * @since 2023-03-27 16:55:00
 */
public interface TagsService extends IService<Tags> {

    ResponseResult getAllTags();

    ResponseResult list(Integer pageNo, Integer pageSize, String name);

    ResponseResult add(Tags tags);

    ResponseResult info(Long id);

    ResponseResult update(Tags tags);

    ResponseResult top(Long id);

    ResponseResult remove(Long id);

    ResponseResult deleteBatch(List<Long> ids);
}


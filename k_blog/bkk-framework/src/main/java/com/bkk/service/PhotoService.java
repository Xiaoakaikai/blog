package com.bkk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.entity.Photo;

import java.util.List;
import java.util.Map;


/**
 * 照片(Photo)表服务接口
 *
 * @author makejava
 * @since 2023-04-13 21:26:22
 */
public interface PhotoService extends IService<Photo> {

    ResponseResult list(Integer pageNo, Integer pageSize, Long albumId);

    ResponseResult add(Photo photo);

    ResponseResult info(Long id);

    ResponseResult update(Photo photo);

    ResponseResult deleteBatch(List<Long> ids);

    ResponseResult movePhoto(Map<String, Object> map);
}


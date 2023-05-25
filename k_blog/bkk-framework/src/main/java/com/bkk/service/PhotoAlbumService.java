package com.bkk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.entity.PhotoAlbum;


/**
 * 相册(PhotoAlbum)表服务接口
 *
 * @author makejava
 * @since 2023-04-13 21:22:48
 */
public interface PhotoAlbumService extends IService<PhotoAlbum> {

    ResponseResult webList();

    ResponseResult list(Integer pageNo, Integer pageSize, String name);

    ResponseResult add(PhotoAlbum photoAlbum);

    ResponseResult info(Long id);

    ResponseResult update(PhotoAlbum photoAlbum);

    ResponseResult delete(Long id);

    ResponseResult listPhotos(Integer pageNo, Integer pageSize, Long albumId);
}


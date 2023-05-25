package com.bkk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.entity.Photo;
import com.bkk.domain.entity.PhotoAlbum;
import com.bkk.mapper.PhotoAlbumMapper;
import com.bkk.mapper.PhotoMapper;
import com.bkk.service.PhotoAlbumService;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bkk.enums.YesOrNoEnum.NO;

/**
 * 相册(PhotoAlbum)表服务实现类
 *
 * @author makejava
 * @since 2023-04-13 21:22:48
 */
@Service("photoAlbumService")
public class PhotoAlbumServiceImpl extends ServiceImpl<PhotoAlbumMapper, PhotoAlbum> implements PhotoAlbumService {

    @Autowired
    private PhotoMapper photoMapper;

    /**
     * 相册列表
     * @return
     */
    @Override
    public ResponseResult webList() {
        List<PhotoAlbum> photoAlbums = baseMapper.selectList(new LambdaQueryWrapper<PhotoAlbum>().select(PhotoAlbum::getId, PhotoAlbum::getName,
                PhotoAlbum::getInfo, PhotoAlbum::getCover).eq(PhotoAlbum::getStatus, NO.getCode()));
        return ResponseResult.okResult(photoAlbums);
    }

    /**
     * 后台获取相册列表
     * @param pageNo
     * @param pageSize
     * @param name
     * @return
     */
    @Override
    public ResponseResult list(Integer pageNo, Integer pageSize, String name) {
        LambdaQueryWrapper<PhotoAlbum> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(name), PhotoAlbum::getName, name);
        Page<PhotoAlbum> page = new Page<>(pageNo, pageSize);
        page(page, queryWrapper);
        page.getRecords().forEach(item -> {
            Integer count = photoMapper.selectCount(new LambdaQueryWrapper<Photo>().eq(Photo::getAlbumId, item.getId()));
            item.setPhotoCount(count);
        });
        return ResponseResult.okResult(page);
    }

    /**
     * 新增相册
     * @param photoAlbum
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult add(PhotoAlbum photoAlbum) {
        int insert = baseMapper.insert(photoAlbum);
        return insert > 0 ? ResponseResult.okResult() : ResponseResult.errorResult("添加失败！");
    }

    /**
     * 相册详情
     * @param id
     * @return
     */
    @Override
    public ResponseResult info(Long id) {
        PhotoAlbum photoAlbum = baseMapper.selectById(id);
        Integer count = photoMapper.selectCount(new LambdaQueryWrapper<Photo>().eq(Photo::getAlbumId, id));
        photoAlbum.setPhotoCount(count);
        return ResponseResult.okResult(photoAlbum);
    }

    /**
     * 修改相册
     * @param photoAlbum
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult update(PhotoAlbum photoAlbum) {
        int update = baseMapper.updateById(photoAlbum);
        return update > 0 ? ResponseResult.okResult() : ResponseResult.errorResult("修改失败！");
    }

    /**
     * 删除相册
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult delete(Long id) {
        int delete = baseMapper.deleteById(id);
        photoMapper.delete(new LambdaQueryWrapper<Photo>().eq(Photo::getAlbumId, id));
        return delete > 0 ? ResponseResult.okResult() : ResponseResult.errorResult("删除失败！");
    }

    /**
     * 前台照片列表
     * @param pageNo
     * @param pageSize
     * @param albumId
     * @return
     */
    @Override
    public ResponseResult listPhotos(Integer pageNo, Integer pageSize, Long albumId) {
        LambdaQueryWrapper<Photo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Photo::getAlbumId, albumId);
        Page<Photo> page = new Page<>(pageNo, pageSize);
        Page<Photo> photoPage = photoMapper.selectPage(page, queryWrapper);

        List<String> urlList = new ArrayList<>();
        photoPage.getRecords().forEach(item -> urlList.add(item.getUrl()));

        PhotoAlbum photoAlbum = baseMapper.selectById(albumId);

        Map<String,Object> result = new HashMap<>();
        result.put("photoList",urlList);
        result.put("photoAlbumCover",photoAlbum.getCover());
        result.put("photoAlbumName",photoAlbum.getName());
        return ResponseResult.okResult(result);
    }


}


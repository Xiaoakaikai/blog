package com.bkk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.entity.Photo;
import com.bkk.mapper.PhotoMapper;
import com.bkk.service.PhotoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 照片(Photo)表服务实现类
 *
 * @author makejava
 * @since 2023-04-13 21:26:22
 */
@Service("photoService")
public class PhotoServiceImpl extends ServiceImpl<PhotoMapper, Photo> implements PhotoService {


    /**
     * 照片列表
     * @param pageNo
     * @param pageSize
     * @param albumId
     * @return
     */
    @Override
    public ResponseResult list(Integer pageNo, Integer pageSize, Long albumId) {
        LambdaQueryWrapper<Photo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Photo::getAlbumId, albumId);
        Page<Photo> page = new Page<>(pageNo, pageSize);
        page(page, queryWrapper);
        return ResponseResult.okResult(page);
    }

    /**
     * 新增照片
     * @param photo
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult add(Photo photo) {
        int insert = baseMapper.insert(photo);
        return insert > 0 ? ResponseResult.okResult() : ResponseResult.errorResult("添加失败！");
    }

    /**
     * 根据id获取照片详情
     * @param id
     * @return
     */
    @Override
    public ResponseResult info(Long id) {
        Photo photo = baseMapper.selectById(id);
        return ResponseResult.okResult(photo);
    }

    /**
     * 修改照片
     * @param photo
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult update(Photo photo) {
        int update = baseMapper.updateById(photo);
        return update > 0 ? ResponseResult.okResult() : ResponseResult.errorResult("修改失败！");
    }

    /**
     * 删除照片
     * @param ids
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult deleteBatch(List<Long> ids) {
        int i = baseMapper.deleteBatchIds(ids);
        return i > 0 ? ResponseResult.okResult() : ResponseResult.errorResult("删除失败！");
    }

    /**
     * 移动照片
     * @param map
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult movePhoto(Map<String, Object> map) {
        Long albumId = Long.parseLong((String) map.get("albumId"));
        List<Long> ids = (List<Long>) map.get("ids");
        baseMapper.movePhoto(ids, albumId);
        return ResponseResult.okResult();
    }
}


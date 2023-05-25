package com.bkk.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bkk.annotation.OperationLogger;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.entity.PhotoAlbum;
import com.bkk.service.PhotoAlbumService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/album")
@Api(tags = "相册管理")
public class PhotoAlbumController {

    @Autowired
    private PhotoAlbumService photoAlbumService;

    @GetMapping("/list")
    @ApiOperation(value = "相册列表", httpMethod = "GET", response = ResponseResult.class, notes = "相册列表")
    public ResponseResult<Page<PhotoAlbum>> list(Integer pageNo, Integer pageSize, String name) {
        return photoAlbumService.list(pageNo, pageSize, name);
    }

    @OperationLogger(value = "添加相册")
    @PostMapping("/add")
    @ApiOperation(value = "添加相册", httpMethod = "POST", response = ResponseResult.class, notes = "添加相册")
    public ResponseResult add(@RequestBody PhotoAlbum photoAlbum) {
        return photoAlbumService.add(photoAlbum);
    }

    @GetMapping("/info")
    @ApiOperation(value = "相册详情", httpMethod = "GET", response = ResponseResult.class, notes = "相册详情")
    public ResponseResult<PhotoAlbum> info(@RequestParam Long id) {
        return photoAlbumService.info(id);
    }

    @OperationLogger(value = "修改相册")
    @PutMapping("/update")
    @ApiOperation(value = "修改相册", httpMethod = "PUT", response = ResponseResult.class, notes = "修改相册")
    public ResponseResult update(@RequestBody PhotoAlbum photoAlbum) {
        return photoAlbumService.update(photoAlbum);
    }

    @OperationLogger(value = "删除相册")
    @DeleteMapping("/delete")
    @ApiOperation(value = "删除相册", httpMethod = "DELETE", response = ResponseResult.class, notes = "删除相册")
    public ResponseResult delete(@RequestParam Long id) {
        return photoAlbumService.delete(id);
    }
}

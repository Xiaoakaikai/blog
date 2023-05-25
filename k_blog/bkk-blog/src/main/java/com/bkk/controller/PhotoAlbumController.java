package com.bkk.controller;

import com.bkk.annotation.BusinessLogger;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.entity.PhotoAlbum;
import com.bkk.service.PhotoAlbumService;
import com.bkk.service.PhotoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/album")
@Api(tags = "前台相册管理")
public class PhotoAlbumController {

    @Autowired
    private PhotoAlbumService photoAlbumService;

    @BusinessLogger(value = "相册-相册列表",type = "查询",desc = "查询相册列表")
    @GetMapping("/list")
    @ApiOperation(value = "相册列表", httpMethod = "GET", response = ResponseResult.class, notes = "相册列表")
    public ResponseResult<List<PhotoAlbum>> list() {
        return photoAlbumService.webList();
    }

    @BusinessLogger(value = "相册-照片列表",type = "查询",desc = "查询照片列表")
    @GetMapping("/listPhotos")
    @ApiOperation(value = "照片列表", httpMethod = "GET", response = ResponseResult.class, notes = "照片列表")
    public ResponseResult<Map<String,Object>> listPhotos(Integer pageNo, Integer pageSize, Long albumId) {
        return photoAlbumService.listPhotos(pageNo, pageSize, albumId);
    }
}

package com.bkk.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bkk.annotation.OperationLogger;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.entity.Photo;
import com.bkk.service.PhotoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/photo")
@Api(tags = "照片管理")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @GetMapping("/list")
    @ApiOperation(value = "照片列表", httpMethod = "GET", response = ResponseResult.class, notes = "照片列表")
    public ResponseResult<Page<Photo>> list(Integer pageNo, Integer pageSize, Long albumId) {
        return photoService.list(pageNo, pageSize, albumId);
    }

    @OperationLogger(value = "添加照片")
    @PostMapping("/add")
    @ApiOperation(value = "添加照片", httpMethod = "POST", response = ResponseResult.class, notes = "添加照片")
    public ResponseResult add(@RequestBody Photo photo) {
        return photoService.add(photo);
    }

    @GetMapping("/info")
    @ApiOperation(value = "照片详情", httpMethod = "GET", response = ResponseResult.class, notes = "照片详情")
    public ResponseResult<Photo> info(@RequestParam Long id) {
        return photoService.info(id);
    }

    @OperationLogger(value = "修改照片")
    @PutMapping("/update")
    @ApiOperation(value = "修改照片", httpMethod = "PUT", response = ResponseResult.class, notes = "修改照片")
    public ResponseResult update(@RequestBody Photo photo) {
        return photoService.update(photo);
    }

    @OperationLogger(value = "删除照片")
    @DeleteMapping("/deleteBatch")
    @ApiOperation(value = "删除照片", httpMethod = "DELETE", response = ResponseResult.class, notes = "删除照片")
    public ResponseResult deleteBatch(@RequestBody List<Long> ids) {
        return photoService.deleteBatch(ids);
    }

    @OperationLogger(value = "移动照片")
    @PostMapping("/movePhoto")
    @ApiOperation(value = "移动照片", httpMethod = "POST", response = ResponseResult.class, notes = "移动照片")
    public ResponseResult movePhoto(@RequestBody Map<String, Object> map) {
        return photoService.movePhoto(map);
    }
}

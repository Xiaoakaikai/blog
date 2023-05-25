package com.bkk.controller;

import com.bkk.annotation.OperationLogger;
import com.bkk.domain.ResponseResult;
import com.bkk.service.UploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
@Api(tags = "前台文件上传")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @PostMapping("/upload")
    @ApiOperation(value = "文件上传", httpMethod = "POST", response = ResponseResult.class, notes = "文件上传")
    public ResponseResult upload(MultipartFile multipartFile) {
        return uploadService.upload(multipartFile);
    }

    @RequestMapping(value = "/delBatchFile",method = RequestMethod.POST)
    @ApiOperation(value = "批量删除文件",httpMethod = "POST", response = ResponseResult.class, notes = "批量删除文件")
    @OperationLogger("批量删除图片")
    public ResponseResult delBatchFile(String key){
        return uploadService.delBatchFile(key);
    }
}

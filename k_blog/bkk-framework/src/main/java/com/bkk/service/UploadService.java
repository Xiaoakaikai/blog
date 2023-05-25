package com.bkk.service;

import com.bkk.domain.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    ResponseResult upload(MultipartFile multipartFile);

    ResponseResult delBatchFile(String key);
}

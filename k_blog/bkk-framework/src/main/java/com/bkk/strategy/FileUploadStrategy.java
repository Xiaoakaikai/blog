package com.bkk.strategy;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传策略
 */
public interface FileUploadStrategy {

    String fileUpload(MultipartFile file, String filePath);

    Boolean deleteFile(String ...fileName);
}

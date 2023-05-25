package com.bkk.strategy.impl;

import com.bkk.strategy.FileUploadStrategy;
import com.bkk.utils.PathUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.Objects;
import java.util.UUID;

@Service("localUploadStrategyImpl")
@RequiredArgsConstructor
public class LocalUploadStrategyImpl implements FileUploadStrategy {

    private final Logger logger = LoggerFactory.getLogger(LocalUploadStrategyImpl.class);

//    private final SystemConfigService systemConfigService;
    @Value("${file.upload-folder}")
    private String UPLOAD_FOLDER;

    @Value("${file.upload-url}")
    private String localFileUrl;

//    @PostConstruct
//    private void init(){
//        SystemConfig systemConfig = systemConfigService.getCustomizeOne();
//        localFileUrl = systemConfig.getLocalFileUrl();
//    }

    @Override
    public String fileUpload(MultipartFile file, String fileName) {
        String savePath = UPLOAD_FOLDER;
        File savePathFile = new File(savePath);
        if (!savePathFile.exists()) {
            //若不存在该目录，则创建目录
            savePathFile.mkdir();
        }
        //获取文件后缀
        String suffix = Objects.requireNonNull(fileName).substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        //uuid作为文件名
        String filename = UUID.randomUUID().toString().replaceAll("-", "") + "." + suffix;
        try {
            //将文件保存指定目录
            file.transferTo(new File(savePath + filename));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //返回文件名称
        return localFileUrl + filename;
    }

    @Override
    public Boolean deleteFile(String... fileName) {
        String savePath = UPLOAD_FOLDER;
        boolean flag = false;
        for (String name : fileName) {
            File file = new File(savePath + name);
            // 路径为文件且不为空则进行删除
            if (file.isFile() && file.exists()) {
                file.delete();
                flag = true;
            }
        }
        return flag;
    }


}

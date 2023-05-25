package com.bkk.strategy.impl;

import com.bkk.domain.entity.SystemConfig;
import com.bkk.service.SystemConfigService;
import com.bkk.strategy.FileUploadStrategy;
import com.bkk.utils.PathUtils;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.InputStream;

@Service("qiNiuUploadStrategyImpl")
@RequiredArgsConstructor
public class QiNiuUploadStrategyImpl implements FileUploadStrategy {

    private final SystemConfigService systemConfigService;

    private String accessKey;

    private String secretKey;

    private String bucket;

    private String url;

    @PostConstruct
    private void init(){
        SystemConfig systemConfig = systemConfigService.getCustomizeOne();
        accessKey = systemConfig.getQiNiuAccessKey();
        secretKey = systemConfig.getQiNiuSecretKey();
        bucket = systemConfig.getQiNiuBucket();
        url = systemConfig.getQiNiuPictureBaseUrl();
    }

    @Override
    public String fileUpload(MultipartFile file, String fileName) {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.autoRegion());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
        //...其他参数参考类注释

        UploadManager uploadManager = new UploadManager(cfg);

        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = PathUtils.generateFilePath(fileName);

        try {
//            byte[] uploadBytes = "hello qiniu cloud".getBytes("utf-8");
//            ByteArrayInputStream byteInputStream=new ByteArrayInputStream(uploadBytes);

            InputStream inputStream = file.getInputStream();
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);

            try {
                Response response = uploadManager.put(inputStream,key,upToken,null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
                return url + key;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (Exception ex) {
            //ignore
        }
        return "";
    }

    @Override
    public Boolean deleteFile(String... fileName) {
        return null;
    }
}

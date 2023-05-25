package com.bkk.service.impl;

import com.bkk.domain.ResponseResult;
import com.bkk.enums.FileUploadModelEnum;
import com.bkk.exception.SystemException;
import com.bkk.service.SystemConfigService;
import com.bkk.service.UploadService;
import com.bkk.strategy.context.FileUploadStrategyContext;
import com.bkk.utils.OssUtils;
import com.bkk.utils.PathUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

import static com.bkk.enums.AppHttpCodeEnum.FAILURE;
import static com.bkk.enums.FileUploadModelEnum.LOCAL;

@Service
@RequiredArgsConstructor
public class UploadServiceImpl implements UploadService {

    private final FileUploadStrategyContext fileUploadStrategyContext;

    private final SystemConfigService systemConfigService;

    private String strategy;

    /**
     * 文件上传
     * @param multipartFile
     * @return
     */
    @Override
    public ResponseResult upload(MultipartFile multipartFile) {
        if (multipartFile.getSize() > 1024 * 1024 * 10) {
            return ResponseResult.errorResult(FAILURE, "文件大小不能大于10M");
        }
        String fileName = multipartFile.getOriginalFilename();
        //获取文件后缀
        String suffix = Objects.requireNonNull(fileName).substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1);
        if (!"jpg,jpeg,gif,png".toUpperCase().contains(suffix.toUpperCase())) {
            return ResponseResult.errorResult(FAILURE, "请选择jpg,jpeg,gif,png格式的图片");
        }
//        String url = OssUtils.upload(multipartFile, filePath);

        getFileUploadWay();
        String url = fileUploadStrategyContext.executeFileUploadStrategy(strategy, multipartFile, fileName);
        return ResponseResult.okResult(url);
    }

    /**
     * 删除文件
     * @param key
     * @return
     */
    @Override
    public ResponseResult delBatchFile(String key) {
        getFileUploadWay();
        Boolean isSuccess = fileUploadStrategyContext.executeDeleteFileStrategy(strategy, key);
        if (!isSuccess) {
            return ResponseResult.errorResult("删除文件失败");
        }
        return ResponseResult.okResult();
    }

    private void getFileUploadWay() {
        strategy = FileUploadModelEnum.getStrategy(systemConfigService.getCustomizeOne().getFileUploadWay());
    }

}

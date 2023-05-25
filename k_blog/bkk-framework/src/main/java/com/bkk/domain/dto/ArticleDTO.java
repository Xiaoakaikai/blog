package com.bkk.domain.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author xak
 */
@Data
@ApiModel("ArticleDTO")
public class ArticleDTO {
    private Long id;
    private Long userId;
    private String title;
    private String avatar;
    private String summary;
    private Integer quantity;
    private String content;
    private String contentMd;
    private Integer isSecret;
    private Integer isStick;
    private Integer isOriginal;
    private String originalUrl;
    private String remark;
    private String keywords;
    private String categoryName;
    private Integer isPublish;
    private List<String> tags;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
}

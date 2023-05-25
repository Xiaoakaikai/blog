package com.bkk.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Accessors(chain = true)
@ApiModel("ArticlePreviewVO")
public class ArticlePreviewVO {

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "封面图片")
    private String avatar;

    @ApiModelProperty(value = "内容MD版")
    private String contentMd;

    @ApiModelProperty(value = "是否置顶")
    private int isStick;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "分类Id")
    private Long categoryId;

    @ApiModelProperty(value = "分类名")
    private String categoryName;

    @ApiModelProperty(value = "标签")
    private List<TagVO> tagVOList;

}

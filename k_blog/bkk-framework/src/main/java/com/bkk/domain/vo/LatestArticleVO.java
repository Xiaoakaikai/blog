package com.bkk.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author blue
 * @date 2022/1/14
 * @apiNote
 */
@Data
@ApiModel("LatestArticleVO")
public class LatestArticleVO {
    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "封面地址")
    private String avatar;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
}

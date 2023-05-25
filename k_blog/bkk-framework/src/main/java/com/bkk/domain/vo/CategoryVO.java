package com.bkk.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(value = "CategoryVO")
public class CategoryVO {

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "分类名")
    private String name;

    @ApiModelProperty(value = "文章数")
    private int articleNum;
}

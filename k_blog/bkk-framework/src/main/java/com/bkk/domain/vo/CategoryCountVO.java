package com.bkk.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("CategoryCountVO")
public class CategoryCountVO {
    @ApiModelProperty("分类数量")
    private Integer value;
    @ApiModelProperty("分类名称")
    private String name;
}
package com.bkk.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("ContributeVO")
public class ContributeVO {
    @ApiModelProperty("贡献数")
    private Integer count;
    @ApiModelProperty("贡献日期")
    private String date;
}

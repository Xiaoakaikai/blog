package com.bkk.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("TagVO")
public class TagVO {
    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;
}

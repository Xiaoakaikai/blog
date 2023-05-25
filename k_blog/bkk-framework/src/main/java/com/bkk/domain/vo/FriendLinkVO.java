package com.bkk.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("FriendLinkVO")
public class FriendLinkVO {

    @ApiModelProperty(value = "网站名称")
    private String name;

    @ApiModelProperty(value = "网站地址")
    private String url;

    @ApiModelProperty(value = "网站头像地址")
    private String avatar;

    @ApiModelProperty(value = "网站描述")
    private String info;

}

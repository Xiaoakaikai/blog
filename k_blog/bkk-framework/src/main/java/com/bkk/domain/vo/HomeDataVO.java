package com.bkk.domain.vo;

import com.bkk.domain.entity.Article;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author xak
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("HomeDataVO")
public class HomeDataVO {
    @ApiModelProperty("贡献")
    private Map<String, Object> contribute;
    @ApiModelProperty("分类列表")
    private Map<String, Object> categoryList;
    @ApiModelProperty("用户访问量")
    private List<Map<String,Object>> userAccess;
    @ApiModelProperty("标签列表")
    private List<Map<String,Object>> tagsList;

    private String dashboard;
    @ApiModelProperty("文章排行")
    private List<Article> blogArticles;
}

package com.bkk.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "blog")
@ApiModel("ArticleSearchVO")
public class ArticleSearchVO {

    /**
     * 文章id
     */
    @Id
    @ApiModelProperty("文章id")
    private Long id;

    /**
     * 文章标题
     */
    @ApiModelProperty("文章标题")
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String title;

    /**
     * 文章内容
     */
    @ApiModelProperty("文章内容")
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String content;
}
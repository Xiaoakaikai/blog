package com.bkk.domain.vo;


import com.bkk.domain.entity.Category;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.xml.stream.events.Comment;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@ApiModel("ArticleInfoVO")
public class ArticleInfoVO {

    /**
     * 主键id
     */
    @ApiModelProperty("主键id")
    private Long id;
    /**
     *分类id
     */
    @ApiModelProperty("分类id")
    private Long categoryId;
    /**
     * 标题
     */
    @ApiModelProperty("标题")
    private String title;
    /**
     * 封面地址
     */
    @ApiModelProperty("封面地址")
    private String avatar;
    /**
     * 内容
     */
    @ApiModelProperty("内容")
    private String content;
    /**
     * 文章内容MD版
     */
    @ApiModelProperty("文章内容MD版")
    private String contentMd;
    /**
     * 是否私密
     */
    @ApiModelProperty("是否私密")
    private Integer isSecret;
    /**
     * 是否原创
     */
    @ApiModelProperty("是否原创")
    private Integer isOriginal;
    /**
     * 转发地址
     */
    @ApiModelProperty("转发地址")
    private String originalUrl;
    /**
     * 阅读量
     */
    @ApiModelProperty("阅读量")
    private Integer quantity;
    /**
     * SEO关键词
     */
    @ApiModelProperty("SEO关键词")
    private String keywords;
    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;
    /**
     * 分类
     */
    @ApiModelProperty("分类")
    private Category category;
    /**
     * 评论集合
     */
    @ApiModelProperty("评论集合")
    private List<Comment> comments = new ArrayList<>();
    /**
     * 标签集合
     */
    @ApiModelProperty("标签集合")
    private List<TagVO> tagList = new ArrayList<>();
    /**
     * 最新文章
     */
    @ApiModelProperty("最新文章")
    private List<LatestArticleVO> newestArticleList = new ArrayList<>();
    /**
     * 上一篇
     */
    @ApiModelProperty("上一篇")
    private LatestArticleVO lastArticle;
    /**
     * 下一篇
     */
    @ApiModelProperty("下一篇")
    private LatestArticleVO nextArticle;
    /**
     * 推荐
     */
    @ApiModelProperty("推荐")
    private List<LatestArticleVO> recommendArticleList = new ArrayList<>();
    /**
     * 点赞量
     */
    @ApiModelProperty("点赞量")
    private Integer likeCount;

}

package com.bkk.constants;

public class RedisConstants {

    /**
     * 邮箱验证码
     */
    public static String EMAIL_CODE = "email_code_";

    /**
     * 验证码有效期（分钟）
     */
    public static final Integer CAPTCHA_EXPIRATION = 5;

    /**
     * 文章阅读key
     */
    public static String ARTICLE_READING = "ARTICLE_READING";

    /**
     * 用户点赞文章
     */
    public static final String ARTICLE_USER_LIKE = "article_user_like:";

    /**
     * 文章点赞量
     */
    public static final String ARTICLE_LIKE_COUNT = "article_like_count";

    /**
     * 访客
     */
    public static final String UNIQUE_VISITOR = "unique_visitor";

    /**
     * 访客地区
     */
    public static final String VISITOR_AREA = "visitor_area";

    /**
     * 博客浏览量
     */
    public static final String BLOG_VIEWS_COUNT = "blog_views_count";
}

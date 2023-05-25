package com.bkk.enums;

public enum AppHttpCodeEnum {
    //成功
    SUCCESS( 200, "SUCCESS" ),
    //失败
    FAILURE( 400, "FAILURE" ),
    /**
     * qq登录错误
     */
    QQ_LOGIN_ERROR(53001, "qq登录错误"),
    /**
     * 微博登录错误
     */
    WEIBO_LOGIN_ERROR(53002, "微博登录错误"),

    GITEE_LOGIN_ERROR(53002, "gitee登录错误"),

    // 系统级别错误码
    ERROR(-1, "操作异常"),
    ERROR_DEFAULT(500,"系统繁忙，请稍后重试"),
    NOT_LOGIN(401, "请先登录!"),
    NO_PERMISSION(-7,"无权限"),
    ERROR_PASSWORD(-8,"用户帐号或者密码错误!"),
    DISABLE_ACCOUNT(-9,"帐号已被禁用!"),
    EMAIL_DISABLE_LOGIN(-12,"该邮箱账号已被管理员禁止登录!"),

    // 服务层面
    EMAIL_ERROR(-10,"邮箱格式不对，请检查后重试!"),
    EMAIL_IS_EXIST(-11,"该邮箱已注册，请直接登录!"),
    PASSWORD_ILLEGAL(-13,"密码格式不合法!"),
    NICKNAME_EXIST(-14, "昵称已存在"),
    ERROR_EXCEPTION_MOBILE_CODE(10003,"验证码不正确或已过期，请重新输入"),
    FILE_UPLOAD_WAY_ERROR(10004,"文件上传方式不合法"),
    FILE_UPLOAD_ERROR(10005,"上传文件失败"),
    ERROR_USER_NOT_EXIST(10009, "用户不存在"),
    ERROR_MUST_REGISTER(10017,"请先注册帐号!"),
    PARAMS_ILLEGAL(10018,"参数不合法!!"),
    CATEGORY_IS_EXIST(10019,"该分类名称已存在!"),
    CATEGORY_IS_TOP(10020,"该分类已经在顶端!!"),
    DATA_TAG_IS_EXIST(10021,"该数据标签已存在!"),
    DATA_TAG_IS_TOP(10024,"该数据标签已经在顶端!!"),
    CRAWLING_ARTICLE_FAILED(10022,"抓取文章失败!"),
    ARTICLE_NOT_EXIST(10023,"数据库未存在该文章!"),
    FRIEND_LINK_IS_TOP(10025,"该友链已经在顶端!!");

    int code;
    String message;

    AppHttpCodeEnum(int code, String errorMessage){
        this.code = code;
        this.message = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return message;
    }
}

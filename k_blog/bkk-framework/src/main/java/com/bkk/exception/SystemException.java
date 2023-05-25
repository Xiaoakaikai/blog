package com.bkk.exception;

import com.bkk.enums.AppHttpCodeEnum;

public class SystemException extends RuntimeException{

    private int code;

    private String message;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return message;
    }

    public SystemException(AppHttpCodeEnum httpCodeEnum) {
        super(httpCodeEnum.getMsg());
        this.code = httpCodeEnum.getCode();
        this.message = httpCodeEnum.getMsg();
    }

    public SystemException(String msg) {
        super(msg);
        this.code = AppHttpCodeEnum.ERROR.getCode();
        this.message = msg;
    }



}

package com.bkk.domain;

import com.bkk.enums.AppHttpCodeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "结果返回类")
public class ResponseResult<T> implements Serializable {

    @ApiModelProperty(value = "状态码")
    private Integer code;
    @ApiModelProperty(value = "返回信息")
    private String message;
    @ApiModelProperty(value = "返回数据")
    private T data;

    public ResponseResult() {
        this.code = AppHttpCodeEnum.SUCCESS.getCode();
        this.message = AppHttpCodeEnum.SUCCESS.getMsg();
    }

    public ResponseResult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public ResponseResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static <T> ResponseResult<T> errorResult(int code, String message) {
        ResponseResult<T> result = new ResponseResult<>();
        return result.error(code, message);
    }
    public static <T> ResponseResult<T> okResult() {
        ResponseResult<T> result = new ResponseResult<>();
        return result;
    }
    public static <T> ResponseResult<T> okResult(int code, String message) {
        ResponseResult<T> result = new ResponseResult<>();
        return result.ok(code, null, message);
    }

    public static <T> ResponseResult<T> okResult(T data) {
        ResponseResult<T> result = setAppHttpCodeEnum(AppHttpCodeEnum.SUCCESS, AppHttpCodeEnum.SUCCESS.getMsg());
        if(data!=null) {
            result.setData(data);
        }
        return result;
    }

    public static <T> ResponseResult<T> errorResult(String message){
        return setAppHttpCodeEnum(AppHttpCodeEnum.FAILURE,message);
    }

    public static <T> ResponseResult<T> errorResult(AppHttpCodeEnum enums){
        return setAppHttpCodeEnum(enums,enums.getMsg());
    }

    public static <T> ResponseResult<T> errorResult(AppHttpCodeEnum enums, String message){
        return setAppHttpCodeEnum(enums,message);
    }

    public static <T> ResponseResult<T> setAppHttpCodeEnum(AppHttpCodeEnum enums){
        return okResult(enums.getCode(),enums.getMsg());
    }

    private static <T> ResponseResult<T> setAppHttpCodeEnum(AppHttpCodeEnum enums, String message){
        return okResult(enums.getCode(),message);
    }

    public ResponseResult<T> error(Integer code, String message) {
        this.code = code;
        this.message = message;
        return this;
    }

    public ResponseResult<?> ok(Integer code, T data) {
        this.code = code;
        this.data = data;
        return this;
    }

    public ResponseResult<T> ok(Integer code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
        return this;
    }

    public ResponseResult<?> ok(T data) {
        this.data = data;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }



}
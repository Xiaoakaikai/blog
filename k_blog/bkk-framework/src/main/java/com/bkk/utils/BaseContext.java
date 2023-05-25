package com.bkk.utils;


/**
 * 基于Thread封装工具类，用于保存和获取登录id
 */
public class BaseContext {

    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Long id) {
        threadLocal.set(id);
    }

    public static Long getCurrentId() {
        return threadLocal.get();
    }

}


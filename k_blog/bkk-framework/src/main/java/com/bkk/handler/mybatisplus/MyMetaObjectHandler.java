package com.bkk.handler.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 用于填充公共字段
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {


    @Override
    public void insertFill(MetaObject metaObject) {
        metaObject.setValue("createTime", LocalDateTime.now());
        if (metaObject.findProperty("updateTime", false) != null) {
            metaObject.setValue("updateTime", LocalDateTime.now());
        }
        if (metaObject.findProperty("lastLoginTime", false) != null) {
            metaObject.setValue("lastLoginTime", LocalDateTime.now());
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        metaObject.setValue("updateTime", LocalDateTime.now());
    }
}
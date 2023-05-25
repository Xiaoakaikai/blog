package com.bkk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.entity.Message;

import java.util.List;


/**
 * (Message)表服务接口
 *
 * @author makejava
 * @since 2023-04-13 20:25:47
 */
public interface MessageService extends IService<Message> {

    ResponseResult list(Integer pageNo, Integer pageSize, String name);

    ResponseResult passBatch(List<Long> ids);

    ResponseResult remove(List<Long> ids);

    ResponseResult webMessage();

    ResponseResult add(Message message);
}


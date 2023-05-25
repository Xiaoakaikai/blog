package com.bkk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.entity.FriendLink;

import java.util.List;


/**
 * 友情链接表(FriendLink)表服务接口
 *
 * @author makejava
 * @since 2023-03-30 17:31:44
 */
public interface FriendLinkService extends IService<FriendLink> {

    ResponseResult friendLinkList();

    ResponseResult list(Integer pageNo, Integer pageSize, String name, Integer status);

    ResponseResult create(FriendLink friendLink);

    ResponseResult update(FriendLink friendLink);

    ResponseResult top(Long id);

    ResponseResult remove(List<Long> ids);

    ResponseResult add(FriendLink friendLink);
}


package com.bkk.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bkk.annotation.OperationLogger;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.entity.FriendLink;
import com.bkk.service.FriendLinkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friend")
@Api(tags = "友链管理")
public class FriendLinkController {

    @Autowired
    private FriendLinkService friendLinkService;

    @GetMapping("/list")
    @ApiOperation(value = "友链列表", httpMethod = "GET", response = ResponseResult.class, notes = "友链列表")
    public ResponseResult<Page<FriendLink>> list(Integer pageNo, Integer pageSize, String name, Integer status) {
        return friendLinkService.list(pageNo, pageSize, name, status);
    }

    @OperationLogger(value = "添加友链")
    @PostMapping("/create")
    @ApiOperation(value = "添加友链", httpMethod = "POST", response = ResponseResult.class, notes = "添加友链")
    public ResponseResult create(@RequestBody FriendLink friendLink) {
        return friendLinkService.create(friendLink);
    }

    @OperationLogger(value = "修改友链")
    @PutMapping("/update")
    @ApiOperation(value = "修改友链", httpMethod = "PUT", response = ResponseResult.class, notes = "修改友链")
    public ResponseResult update(@RequestBody FriendLink friendLink) {
        return friendLinkService.update(friendLink);
    }

    @OperationLogger(value = "置顶友链")
    @GetMapping("/top")
    @ApiOperation(value = "置顶友链", httpMethod = "GET", response = ResponseResult.class, notes = "置顶友链")
    public ResponseResult top(@RequestParam Long id) {
        return friendLinkService.top(id);
    }

    @OperationLogger(value = "删除友链")
    @DeleteMapping("/remove")
    @ApiOperation(value = "删除友链", httpMethod = "DELETE", response = ResponseResult.class, notes = "删除友链")
    public ResponseResult remove(@RequestBody List<Long> ids) {
        return friendLinkService.remove(ids);
    }
}

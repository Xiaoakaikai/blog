package com.bkk.controller;

import com.bkk.annotation.BusinessLogger;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.entity.FriendLink;
import com.bkk.domain.vo.FriendLinkVO;
import com.bkk.service.FriendLinkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friend")
@Api(tags = "前台友链列表")
public class FriendLinkController {

    @Autowired
    private FriendLinkService friendLinkService;

    @BusinessLogger(value = "友链模块",type = "查询",desc = "查询所有友链")
    @GetMapping("/list")
    @ApiOperation(value = "友链列表", httpMethod = "GET", response = ResponseResult.class, notes = "友链列表")
    public ResponseResult<List<FriendLinkVO>> friendLinkList() {
        return friendLinkService.friendLinkList();
    }

    @BusinessLogger(value = "友链模块",type = "添加",desc = "申请添加友链")
    @PostMapping("/add")
    @ApiOperation(value = "友链申请", httpMethod = "POST", response = ResponseResult.class, notes = "友链申请")
    public ResponseResult add(@RequestBody FriendLink friendLink) {
        return friendLinkService.add(friendLink);
    }
}

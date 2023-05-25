package com.bkk.controller;

import com.bkk.domain.ResponseResult;
import com.bkk.domain.vo.HomeDataVO;
import com.bkk.service.HomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/home")
@Api(tags = "首页")
public class HomeController {

    @Autowired
    private HomeService homeService;

    @GetMapping("/init")
    @ApiOperation(value = "首页初始化", httpMethod = "GET", response = ResponseResult.class, notes = "首页初始化")
    public ResponseResult<HomeDataVO> init() {
        return homeService.init();
    }

    @GetMapping("/lineCount")
    @ApiOperation(value = "统计数据", httpMethod = "GET", response = ResponseResult.class, notes = "统计数据")
    public ResponseResult<Map<String,Integer>> lineCount() {
        return homeService.lineCount();
    }

    @GetMapping("/report")
    @ApiOperation(value = "增加访问量", httpMethod = "GET", response = ResponseResult.class, notes = "增加访问量")
    public ResponseResult report(HttpServletRequest request) {
        return homeService.report(request);
    }
}

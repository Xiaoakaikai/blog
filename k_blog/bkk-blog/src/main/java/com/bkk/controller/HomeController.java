package com.bkk.controller;

import com.bkk.annotation.BusinessLogger;
import com.bkk.domain.ResponseResult;
import com.bkk.service.HomeService;
import com.bkk.service.WebConfigService;
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
@Api(tags = "前台网站初始化")
public class HomeController {

    @Autowired
    private WebConfigService webConfigService;

    @Autowired
    private HomeService homeService;

    @BusinessLogger(value = "网站信息",type = "查询",desc = "查询网站信息")
    @GetMapping("/webSiteInfo")
    @ApiOperation(value = "网站信息", httpMethod = "GET", response = ResponseResult.class, notes = "网站信息")
    public ResponseResult<Map<String, Object>> webSiteInfo() {
        return webConfigService.webSiteInfo();
    }


    @GetMapping("/report")
    @ApiOperation(value = "增加访问量", httpMethod = "GET", response = ResponseResult.class, notes = "增加访问量")
    public ResponseResult report(HttpServletRequest request) {
        return homeService.report(request);
    }
}

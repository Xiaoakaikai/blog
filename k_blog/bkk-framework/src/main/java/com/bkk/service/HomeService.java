package com.bkk.service;

import com.bkk.domain.ResponseResult;

import javax.servlet.http.HttpServletRequest;

public interface HomeService {
    ResponseResult init();

    ResponseResult lineCount();

    ResponseResult report(HttpServletRequest request);
}

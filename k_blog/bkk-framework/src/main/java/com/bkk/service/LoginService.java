package com.bkk.service;

import com.bkk.domain.ResponseResult;
import com.bkk.domain.dto.LoginDTO;

public interface LoginService {
    ResponseResult login(LoginDTO dto);


    ResponseResult logout();
}

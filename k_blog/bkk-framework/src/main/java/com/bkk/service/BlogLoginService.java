package com.bkk.service;

import com.bkk.domain.ResponseResult;
import com.bkk.domain.dto.EmailLoginDTO;
import com.bkk.domain.dto.LoginDTO;

public interface BlogLoginService {
    ResponseResult login(EmailLoginDTO emailLoginDTO);

    ResponseResult logout();
}

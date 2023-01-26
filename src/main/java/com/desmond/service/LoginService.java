package com.desmond.service;

import com.desmond.domain.ResponseResult;
import com.desmond.domain.User;

public interface LoginService {
    ResponseResult login (User user);
    ResponseResult logout();
}

package com.desmond.dao;

import com.desmond.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    User findByUsername(String username);
}
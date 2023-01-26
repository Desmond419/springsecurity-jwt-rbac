package com.desmond.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PermissionDao {
    List<String> getPermissionByUserId(String userId);
}
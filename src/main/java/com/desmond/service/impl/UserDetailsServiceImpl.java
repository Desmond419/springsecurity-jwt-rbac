package com.desmond.service.impl;

import com.desmond.domain.LoginUser;
import com.desmond.domain.User;
import com.desmond.dao.PermissionDao;
import com.desmond.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名查询用户信息
        User user = userDao.findByUsername(username);

        // 如果查询不到数据就通过抛出异常来给出提示
        if(Objects.isNull(user)){
            throw new RuntimeException("用户名或密码错误");
        }

        // 根据用户id查询权限信息添加到LoginUser中
        List<String> permissionKeyList = permissionDao.getPermissionByUserId(user.getId());

        //封装成UserDetails对象返回
        return new LoginUser(user, permissionKeyList);
    }
}

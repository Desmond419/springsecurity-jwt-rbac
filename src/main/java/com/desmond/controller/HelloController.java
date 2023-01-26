package com.desmond.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    @PreAuthorize("hasAuthority('system:dept:list')")
    public String hello(){
        return "hello";
    }

    @RequestMapping("/hello2")
    @PreAuthorize("hasAnyAuthority('admin','system:dept:list')")
    public String hello2(){
        return "hello2";
    }

    @RequestMapping("/hello3")
    @PreAuthorize("@auth.hasAuthority('system:salary:list')") // 使用自定义权限校验方法
    public String hello3(){
        return "hello3";
    }
}

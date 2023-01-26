package com.desmond.jwt;

import com.desmond.domain.LoginUser;
import com.desmond.jwt.JwtUtil;
import com.desmond.utils.RedisCache;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if (!StringUtils.hasText(header)) {
            filterChain.doFilter(request, response);
            return;
        }

        if(header.startsWith("Bearer ")) {
            String token = header.substring(7);
            String userid;
            try {
                Claims claims = JwtUtil.parseJWT(token);
                userid = claims.getSubject();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("token非法");
            }

            // 从redis中获取用户信息
            String redisKey = "LoginUser: " + userid;
            LoginUser loginUser = redisCache.getCacheObject(redisKey);
            if (Objects.isNull(loginUser)) {
                throw new RuntimeException("用户未登录");
            }

            // 存入SecurityContextHolder，获取权限信息封装到Authentication
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            filterChain.doFilter(request, response);
        } else {
            throw new RuntimeException("令牌没有以Bearer字符串开头");
        }
    }
}
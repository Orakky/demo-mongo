package com.example.demo.register.filter;

import com.example.demo.register.service.impl.UserServiceImpl;
import com.example.demo.utils.RedisUtils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 自定义请求过滤器 token没有或者不正确的时候
 * 告诉用户执行相应操作 token正确且未认证的情况下则放行请求
 * 交由认证过滤器进行认证操作
 */
public class OncePerRequestAuthoricationFilter extends BasicAuthenticationFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(OncePerRequestAuthoricationFilter.class);


    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserServiceImpl userServiceImpl;


    public OncePerRequestAuthoricationFilter(AuthenticationManager authenticationManager, RedisUtil redisUtil, UserServiceImpl userServiceImpl) {
        super(authenticationManager);
        this.redisUtil = redisUtil;
        this.userServiceImpl=userServiceImpl;
    }



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        super.doFilterInternal(request, response, chain);
    }
}

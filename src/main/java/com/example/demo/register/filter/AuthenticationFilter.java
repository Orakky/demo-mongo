package com.example.demo.register.filter;


import com.example.demo.register.handler.AuthenticationLogout;
import com.example.demo.utils.RedisUtils.RedisUtil;
import com.example.demo.utils.WebUtils.JsonData;
import com.example.demo.utils.WebUtils.WebUtils;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 自定义认证过滤器，判断认证成功还是失败，并给予相应的逻辑处理
 */
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RedisUtil redisUtil;


    public AuthenticationFilter(AuthenticationManager authenticationManager, RedisUtil redisUtil) {
        this.authenticationManager = authenticationManager;
        this.redisUtil = redisUtil;
    }


    /**
     * 未认证时调用此方法 判断认证是否成功 认证成功与否由authenticaitionManager.authenticate()去判断
     * 我们只负责传递参数
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password,new ArrayList<>()));

    }


    /**
     * 验证成功操作
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        //验证成功则向redis缓存写入token 然后在响应头添加token，并向前端返回
        String token = UUID.randomUUID().toString().replace("-","");//token的本质是随机生成的字符串
        redisUtil.set(token, request.getParameter("username"),60*10);
        response.setHeader("token",token);
        WebUtils.sendJsonMessage(response, JsonData.buildSuccess(token));
    }


    /**
     * 验证失败
     * @param request
     * @param response
     * @param failed
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        //验证成功则向前端返回失败信息
        WebUtils.sendJsonMessage(response,JsonData.buildError("验证失败，账号或者密码出错!"));
    }
}

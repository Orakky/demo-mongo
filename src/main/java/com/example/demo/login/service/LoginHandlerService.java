package com.example.demo.login.service;


import com.example.demo.login.bean.User;
import com.example.demo.utils.Response.RespVo;

/**
 * 登录拦截器服务接口
 */

public interface LoginHandlerService {


    /**
     * 根据userid和password 登录
     * @param user
     * @return
     */
    RespVo login(User user);
}

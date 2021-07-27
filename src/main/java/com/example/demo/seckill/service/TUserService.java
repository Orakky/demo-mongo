package com.example.demo.seckill.service;


import com.example.demo.seckill.bean.LoginVo;
import com.example.demo.utils.Response.RespVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface TUserService {

    /**
     * 功能描述：登录
     * @param loginVo
     * @param request
     * @param response
     * @return
     */
    RespVo doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response);
}

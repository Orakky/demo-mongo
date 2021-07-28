package com.example.demo.seckill.service;


import com.example.demo.seckill.bean.LoginVo;
import com.example.demo.seckill.bean.TUser;
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


    /**
     * 根据cookie获取用户
     * @param userTicket
     * @return
     */
    TUser getTuseByCookie(String userTicket,HttpServletRequest request,HttpServletResponse response);
}

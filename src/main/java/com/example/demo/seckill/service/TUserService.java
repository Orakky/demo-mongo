package com.example.demo.seckill.service;


import com.example.demo.seckill.bean.LoginVo;
import com.example.demo.utils.Response.RespVo;

public interface TUserService {

    /**
     * 功能描述：登录
     * @param loginVo
     * @return
     */
    RespVo doLogin(LoginVo loginVo);
}

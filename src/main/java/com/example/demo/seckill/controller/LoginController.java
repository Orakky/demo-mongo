package com.example.demo.seckill.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.seckill.bean.LoginVo;
import com.example.demo.seckill.service.TUserService;
import com.example.demo.utils.Response.RespVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * 登录页面跳转controller
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);


    @Autowired
    private TUserService tuserService;

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/doLogin")
    @ResponseBody
    public RespVo doLogin( @RequestBody  LoginVo loginVo, HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("{}", JSONObject.toJSON(loginVo));
        return tuserService.doLogin(loginVo,request,response);
    }

}

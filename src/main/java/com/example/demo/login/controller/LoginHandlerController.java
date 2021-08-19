package com.example.demo.login.controller;


import com.example.demo.login.bean.User;
import com.example.demo.login.service.LoginHandlerService;
import com.example.demo.utils.Response.RespVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试拦截器 实现登录拦截
 */
@RestController
@RequestMapping("/loginHandler")
public class LoginHandlerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginHandlerController.class);


    @Autowired
    private LoginHandlerService loginHandlerService;

    @RequestMapping("/login")
    public RespVo login(@RequestBody User user){

        LOGGER.info("进入loginHandler : {}",user);

        return loginHandlerService.login(user);
    }



}

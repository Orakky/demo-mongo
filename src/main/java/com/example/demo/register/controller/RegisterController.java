package com.example.demo.register.controller;

import com.example.demo.register.bean.SysUser;
import com.example.demo.register.service.UserService;
import com.example.demo.register.service.impl.UserServiceImpl;
import com.example.demo.utils.WebUtils.JsonData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

/**
 * 注册登录controller
 */
@RestController
public class RegisterController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    private UserServiceImpl userServiceImpl;


    /**
     * 注册用户
     * @param sysUser
     * @return
     */
    @PostMapping("/register")
    public JsonData register(@RequestBody SysUser sysUser){
        return JsonData.buildSuccess(userServiceImpl.register(sysUser));
    }


    /**
     * 当权限为ROLE_ADMIN时可访问，否则抛出权限不足
     * @return
     */
    @GetMapping("/admin")
    public JsonData index(){
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails){
            username = ((UserDetails) principal).getUsername();
        }else{
            username = principal.toString();
        }
        return JsonData.buildSuccess(username);
    }

    @GetMapping("/user")
    public JsonData user(){
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return JsonData.buildSuccess(username);

    }

    @RequestMapping("/pub")
    public JsonData pub(){
        String username;
        //获取当前用户信息
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return JsonData.buildSuccess(username);


    }


}

package com.example.demo.register.service;


import com.example.demo.register.bean.SysUser;
import org.springframework.stereotype.Service;

/**
 * sysUserService
 */

public interface SysUserService {

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    SysUser selectByName(String username);


    /**
     * 注册一个新的用户
     * @param user
     * @return
     */
    int insert(SysUser user);
}

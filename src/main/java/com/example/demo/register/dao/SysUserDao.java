package com.example.demo.register.dao;


import com.example.demo.register.bean.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserDao {


    /**
     * 根据名称查找用户
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

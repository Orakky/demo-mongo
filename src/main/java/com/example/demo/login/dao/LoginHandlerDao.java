package com.example.demo.login.dao;

import com.example.demo.login.bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 登录拦截器dao
 */
@Mapper
public interface LoginHandlerDao {


    /**
     * 根据账号查询用户
     * @param user
     * @return
     */
    User queryUserById(User user);


    /**
     * 查询出所有的user列表
     * @return
     */
//    List<User> queryAllUsers();
}

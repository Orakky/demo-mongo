package com.example.demo.register.service.impl;

import com.example.demo.register.bean.SysUser;
import com.example.demo.register.dao.SysUserDao;
import com.example.demo.register.service.SysUserService;
import com.example.demo.utils.RedisUtils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * sysUserServiceImpl
 */
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    @Override
    public SysUser selectByName(String username) {

        SysUser user = (SysUser) redisUtil.hashGet("SysUserService","selectByName"+username);
        if(null == user){
            user = sysUserDao.selectByName(username);
            //设置过期时间
            redisUtil.hashSet("SysUserService","selectByName"+username,user,180);
        }
        return user;
    }

    /**
     * 注册一个新的用户
     *
     * @param user
     * @return
     */
    @Override
    public int insert(SysUser user) {
        return sysUserDao.insert(user);
    }
}

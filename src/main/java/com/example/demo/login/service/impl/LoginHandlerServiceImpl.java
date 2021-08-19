package com.example.demo.login.service.impl;

import com.example.demo.login.bean.User;
import com.example.demo.login.dao.LoginHandlerDao;
import com.example.demo.login.service.LoginHandlerService;
import com.example.demo.utils.CookieUtils.UUIDUtil;
import com.example.demo.utils.RedisUtils.RedisUtil;
import com.example.demo.utils.Response.RespVo;
import com.example.demo.utils.Response.RespVoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 登录拦截器服务接口
 */
@Service
public class LoginHandlerServiceImpl implements LoginHandlerService {


    @Autowired
    private LoginHandlerDao loginHandlerDao;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 根据userid和password 登录
     *
     * @param user
     * @return
     */
    @Override
    public RespVo login(User user) {

       //先判断是否有该用户
        User loginUser = loginHandlerDao.queryUserById(user);

        if(loginUser == null){
            return RespVo.error(RespVoEnum.LOGIN_ERROR);
        }

        //再判断redis中是否缓存了该用户
        boolean redisFlag = redisUtil.hasKey("userId: " + loginUser.getUserId());

        if(redisFlag){
            //如果存在 则说明已经登陆
            return RespVo.error(RespVoEnum.LOGIN_REPEAT);
        }
        //如果不存在 则将对应的loginUser存入redis
        String ticket = UUIDUtil.uuid();
       redisUtil.set("userId: "+loginUser.getUserId(),ticket,10);
        return RespVo.success();
    }
}

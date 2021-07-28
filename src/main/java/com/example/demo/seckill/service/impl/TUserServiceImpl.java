package com.example.demo.seckill.service.impl;

import com.example.demo.seckill.bean.LoginVo;
import com.example.demo.seckill.bean.TUser;
import com.example.demo.seckill.dao.TUserDao;
import com.example.demo.seckill.exception.GlobalException;
import com.example.demo.seckill.service.TUserService;
import com.example.demo.utils.CookieUtils.CookieUtil;
import com.example.demo.utils.CookieUtils.UUIDUtil;
import com.example.demo.utils.Md5Utils.MD5Util;
import com.example.demo.utils.RedisUtils.RedisUtil;
import com.example.demo.utils.Response.RespVo;
import com.example.demo.utils.Response.RespVoEnum;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Service
public class TUserServiceImpl implements TUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TUserServiceImpl.class);


    @Autowired
    private TUserDao tUserDao;

    @Autowired
    private RedisUtil redisUtil;


    /**
     * 功能描述：登录
     *
     * @param loginVo
     * @param request
     * @param response
     * @return
     */
    @Override
    public RespVo doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response) {

        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();

//        //参数校验
//        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)){
//            return RespVo.error(RespVoEnum.LOGIN_ERROR);
//        }
//        if(ValidateUtil.isMobile(mobile)){
//            return RespVo.error(RespVoEnum.MOBILE_ERROR);
//        }
        TUser tUser = tUserDao.selectById(mobile);

        if(tUser == null){
//            return RespVo.error(RespVoEnum.LOGIN_ERROR);
            throw new GlobalException(RespVoEnum.LOGIN_ERROR);
        }

        //判断密码是否正确
        if(!MD5Util.fromPassToDBPass(password,tUser.getSlat()).equals(tUser.getPassword())){
//            return RespVo.error(RespVoEnum.LOGIN_ERROR);
            throw new GlobalException(RespVoEnum.LOGIN_ERROR);
        }

        //生成cookie
        String ticket = UUIDUtil.uuid();

        //将用户信息存储到session
//        request.getSession().setAttribute(ticket,tUser);

        //将用户信息存储到redis
        redisUtil.set("user:" + ticket,tUser);

        CookieUtil.setCookie(request,response,"userTicket",ticket);

        return RespVo.success();
    }


    /**
     * 根据cookie获取用户
     *
     * @param userTicket
     * @return
     */
    @Override
    public TUser getTuseByCookie(String userTicket,HttpServletRequest request,HttpServletResponse response) {
        if(StringUtils.isEmpty(userTicket)){
            return null;
        }

        TUser tUser = (TUser) redisUtil.get("user:" + userTicket);

        if(tUser != null){
            CookieUtil.setCookie(request,response,"userTicket",userTicket);
        }
        return tUser;
    }
}

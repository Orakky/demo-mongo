package com.example.demo.seckill.controller;

import com.example.demo.seckill.bean.TUser;
import com.example.demo.seckill.service.TUserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 商品页面controller
 */

@Controller
@RequestMapping("/goods")
public class GoodsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsController.class);


    @Autowired
    private TUserService tUserService;

    /**
     * 页面跳转 跳转到
     * @return
     */
    @RequestMapping("/toList")
    public String toList(HttpServletRequest request, HttpServletResponse  response, Model model, @CookieValue("userTicket") String ticket){

        if(StringUtils.isEmpty(ticket)){
            return "login";
        }

        //通过session获取
//        TUser tUser = (TUser) session.getAttribute(ticket);

        TUser tUser = tUserService.getTuseByCookie(ticket, request, response);

        if(null == tUser){
            return "login";
        }
        model.addAttribute("user",tUser);
        return  "goodsList";
    }



    @RequestMapping("/toDetail")
    public String toDetail(Model model,TUser tUser){

        model.addAttribute("user",tUser);
            return "goodsList";
    }

}

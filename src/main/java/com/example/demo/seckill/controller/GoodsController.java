package com.example.demo.seckill.controller;

import com.example.demo.seckill.bean.TUser;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * 商品页面controller
 */

@Controller
@RequestMapping("/goods")
public class GoodsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsController.class);


    /**
     * 页面跳转 跳转到
     * @return
     */
    @RequestMapping("/toList")
    public String toList(HttpSession session, Model model, @CookieValue("userTicket") String ticket){

        if(StringUtils.isEmpty(ticket)){
            return "login";
        }
        TUser tUser = (TUser) session.getAttribute(ticket);

        if(null == tUser){
            return "login";
        }
        model.addAttribute("user",tUser);
        return  "goodsList";
    }
}

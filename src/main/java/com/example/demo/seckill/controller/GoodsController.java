package com.example.demo.seckill.controller;

import com.example.demo.seckill.bean.GoodsVo;
import com.example.demo.seckill.bean.TUser;
import com.example.demo.seckill.service.TGoodsService;
import com.example.demo.seckill.service.TUserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * 商品页面controller
 */

@Controller
@RequestMapping("/goods")
public class GoodsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsController.class);


    @Autowired
    private TUserService tUserService;

    @Autowired
    private TGoodsService tGoodsService;

    /**
     * 页面跳转 跳转到
     * @return
     */
    @RequestMapping("/toList1")
    public String toList1(HttpServletRequest request, HttpServletResponse  response, Model model, @CookieValue("userTicket") String ticket){

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


    /**
     * 商品列表
     * @param model
     * @param tUser
     * @return
     */
    @RequestMapping("/toList")
    public String toList(Model model,TUser tUser){

        model.addAttribute("user",tUser);
        model.addAttribute("goodsList",tGoodsService.findGoodsVo());
            return "goodsList";
    }


    /**
     * 跳转至商品详情页面
     */
    @RequestMapping("/toDetail")
    public String toDetail(Model model,TUser tUser,@PathVariable Long goodsId){
        model.addAttribute("user",tUser);
        GoodsVo goodsVo = tGoodsService.findGoodsVoByGoodsId(goodsId);
        Date startDate = goodsVo.getStartDate();
        Date endDate = goodsVo.getEndDate();
        Date nowDate = new Date();

        //秒杀状态
        int secKillStatus = 0;

        //秒杀倒计时
        int remainSeconds = 0;

        if(nowDate.before(startDate)){
            remainSeconds = (int) (startDate.getTime() - nowDate.getTime()) / 1000;
        }else if(nowDate.after(endDate)){
            secKillStatus  = 2;//秒杀已结束
            remainSeconds = -1;
        }else {
            secKillStatus = 1;
            remainSeconds = 0;
        }
        model.addAttribute("remainSeconds",remainSeconds);
        model.addAttribute("secKillStatus",secKillStatus);
        model.addAttribute("goods",goodsVo);
        return "goodsDetail";

    }
}

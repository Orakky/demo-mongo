package com.example.demo.seckill.controller;

import com.example.demo.seckill.bean.GoodsVo;
import com.example.demo.seckill.bean.TUser;
import com.example.demo.seckill.service.TGoodsService;
import com.example.demo.utils.Response.RespVoEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.ws.Action;

/**
 * 秒杀系统controller
 */

@Controller
@RequestMapping("/seckill")
public class SecKillController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecKillController.class);



    @Autowired
    private TGoodsService tGoodsService;




    @RequestMapping("/hello")
    public String hello(Model model){
        model.addAttribute("name ","orakky");
        LOGGER.info("跳转至hello界面");
        return "hello";
    }


    /**
     * 秒杀
     * @param model
     * @param tUser
     * @param goodsId
     * @return
     */
    @RequestMapping("/doSecKill")
    public String doSecKill(Model model, TUser tUser, Long goodsId){
        if(tUser == null){
            return "login";
        }
        model.addAttribute("user",tUser);
        GoodsVo goodsVo = tGoodsService.findGoodsVoByGoodsId(goodsId);

        //判断库存
        if(goodsVo.getStockCount() < 1){
            model.addAttribute("errorMsg", RespVoEnum.EMPTY_STOCK.getMessage());
            return "secKillFail";
        }




        return "doSecKill";

    }


}

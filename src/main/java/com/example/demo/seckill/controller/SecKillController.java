package com.example.demo.seckill.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 秒杀系统controller
 */

@Controller
@RequestMapping("/seckill")
public class SecKillController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecKillController.class);

    @RequestMapping("/hello")
    public String hello(Model model){
        model.addAttribute("name ","orakky");
        LOGGER.info("跳转至hello界面");
        return "hello";
    }
}

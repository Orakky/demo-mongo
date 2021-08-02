package com.example.demo.seckill.service;

import com.example.demo.seckill.bean.GoodsVo;
import com.example.demo.seckill.bean.TOrder;
import com.example.demo.seckill.bean.TUser;

/**
 * 订单服务层
 */
public interface TOrderService {

    /**
     * 秒杀
     * @param tUser
     * @param goods
     * @return
     */
    TOrder secKill(TUser tUser, GoodsVo goods);
}

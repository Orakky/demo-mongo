package com.example.demo.seckill.service;


import com.example.demo.seckill.bean.TSeckillOrder;

public interface TSeckillOrderService {


    /**
     * 判断是否重复抢购
     * @param userId
     * @param goodsId
     * @return
     */
    TSeckillOrder getOne(String userId, Long goodsId);


    /**
     *  保存订单
     * @param tSeckillOrder
     */
    Integer save(TSeckillOrder tSeckillOrder);
}

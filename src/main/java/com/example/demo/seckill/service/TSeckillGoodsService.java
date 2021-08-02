package com.example.demo.seckill.service;

import com.example.demo.seckill.bean.TSeckillGoods;

public interface TSeckillGoodsService {

    /**
     * 根据商品id拿到秒杀商品
     * @param goodsId
     * @return
     */
    TSeckillGoods getOne(Long goodsId);


    /**
     * 根据id更新秒杀商品
     * @param tSeckillGoods
     */
    Integer updateById(TSeckillGoods tSeckillGoods);
}

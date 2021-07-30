package com.example.demo.seckill.service;


import com.example.demo.seckill.bean.GoodsVo;

import java.util.List;

/**
 * 商品service
 */
public interface TGoodsService {
    /**
     * 获取全部的商品对象
     * @return
     */
    List<GoodsVo> findGoodsVo();


    /**
     * 获取商品详情
     * @param goodsId
     * @return
     */
    GoodsVo findGoodsVoByGoodsId(Long goodsId);
}

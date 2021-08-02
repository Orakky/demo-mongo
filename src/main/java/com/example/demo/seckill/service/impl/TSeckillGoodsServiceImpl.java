package com.example.demo.seckill.service.impl;

import com.example.demo.seckill.bean.TSeckillGoods;
import com.example.demo.seckill.dao.TSeckillGoodsDao;
import com.example.demo.seckill.service.TSeckillGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TSeckillGoodsServiceImpl implements TSeckillGoodsService {


    @Autowired
    private TSeckillGoodsDao tSeckillGoodsDao;

    /**
     * 根据商品id拿到秒杀商品
     *
     * @param goodsId
     * @return
     */
    @Override
    public TSeckillGoods getOne(Long goodsId) {
        return tSeckillGoodsDao.getOne(goodsId);
    }


    /**
     * 根据id更新秒杀商品
     *
     * @param tSeckillGoods
     */
    @Override
    public Integer updateById(TSeckillGoods tSeckillGoods) {
        return  tSeckillGoodsDao.updateByPrimaryKey(tSeckillGoods);
    }
}

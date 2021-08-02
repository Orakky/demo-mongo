package com.example.demo.seckill.service.impl;

import com.example.demo.seckill.bean.TSeckillOrder;
import com.example.demo.seckill.dao.TSeckillOrderDao;
import com.example.demo.seckill.service.TSeckillOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 秒杀系统服务层
 */
@Service
public class TSeckillOrderServiceImpl implements TSeckillOrderService {


    @Autowired
    private TSeckillOrderDao tSeckillOrderDao;

    /**
     * 判断是否重复抢购
     *
     * @param userId
     * @param goodsId
     * @return
     */
    @Override
    public TSeckillOrder getOne(String userId, Long goodsId) {
        return tSeckillOrderDao.getOne(userId,goodsId);
    }


    /**
     * 保存订单
     *
     * @param tSeckillOrder
     */
    @Override
    public Integer save(TSeckillOrder tSeckillOrder) {
       return  tSeckillOrderDao.insert(tSeckillOrder);
    }
}

package com.example.demo.seckill.service.impl;

import com.example.demo.seckill.bean.*;
import com.example.demo.seckill.dao.TOrderDao;
import com.example.demo.seckill.service.TOrderService;
import com.example.demo.seckill.service.TSeckillGoodsService;
import com.example.demo.seckill.service.TSeckillOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 订单服务层
 */
@Service
public class TOrderServiceImpl implements TOrderService {



    @Autowired
    private TSeckillGoodsService tSeckillGoodsService;

    @Autowired
    private TOrderDao tOrderDao;

    @Autowired
    private TSeckillOrderService tSeckillOrderService;
    

    /**
     * 实现 秒杀
     * @param tUser
     * @param goods
     * @return
     */
    @Override
    public TOrder secKill(TUser tUser, GoodsVo goods) {
        //根据商品id拿到秒杀商品
        TSeckillGoods tSeckillGoods = tSeckillGoodsService.getOne(goods.getId());

        tSeckillGoods.setStockCount(tSeckillGoods.getStockCount()-1);

        Integer flag = tSeckillGoodsService.updateById(tSeckillGoods);
        if(flag == 1){
            //生成订单
            TOrder tOrder = new TOrder();
            tOrder.setUserId(Long.valueOf(tUser.getId()));
            tOrder.setGoodsId(goods.getId());
            tOrder.setDeliveryAddrId(0L);
            tOrder.setGoodsName(goods.getGoodsName());
            tOrder.setGoodsCount(1);
            tOrder.setGoodsPrice(tSeckillGoods.getSeckillPrice());
            tOrder.setOrderChannel(1);
            tOrder.setStatus(0);
            tOrder.setCreateDate(new Date());
            int insertFlag= tOrderDao.insert(tOrder);
            //生成秒杀订单
            TSeckillOrder tSeckillOrder = new TSeckillOrder();
            tSeckillOrder.setUserId(Long.valueOf(tUser.getId()));
            tSeckillOrder.setOrderId(tOrder.getId());
            tSeckillOrder.setGoodsId(goods.getId());
            tSeckillOrderService.save(tSeckillOrder);
            return tOrder;
        }

        return null;
    }
}

package com.example.demo.seckill.dao;

import com.example.demo.seckill.bean.TSeckillGoods;

public interface TSeckillGoodsDao {
    int deleteByPrimaryKey(Long id);

    int insert(TSeckillGoods record);

    int insertSelective(TSeckillGoods record);

    TSeckillGoods selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TSeckillGoods record);

    int updateByPrimaryKey(TSeckillGoods record);
}
package com.example.demo.seckill.dao;

import com.example.demo.seckill.bean.TSeckillGoods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface TSeckillGoodsDao {
    int deleteByPrimaryKey(Long id);

    int insert(TSeckillGoods record);

    int insertSelective(TSeckillGoods record);

    TSeckillGoods selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TSeckillGoods record);

    int updateByPrimaryKey(TSeckillGoods record);

    /**
     * 根据goodsId拿到秒杀商品
     * @param goodsId
     * @return
     */
    TSeckillGoods getOne(@Param("goodsId") Long goodsId);
}
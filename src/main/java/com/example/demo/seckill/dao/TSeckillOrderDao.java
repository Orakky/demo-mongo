package com.example.demo.seckill.dao;

import com.example.demo.seckill.bean.TSeckillOrder;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface TSeckillOrderDao {
    int deleteByPrimaryKey(Long id);

    int insert(TSeckillOrder record);

    int insertSelective(TSeckillOrder record);

    TSeckillOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TSeckillOrder record);

    int updateByPrimaryKey(TSeckillOrder record);


    /**
     * 判断是否重复抢购
     * @param userId
     * @param goodsId
     * @return
     */
    TSeckillOrder getOne(String userId, Long goodsId);
}
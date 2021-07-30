package com.example.demo.seckill.dao;

import com.example.demo.seckill.bean.GoodsVo;
import com.example.demo.seckill.bean.TGoods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface TGoodsDao {
    int deleteByPrimaryKey(Long id);

    int insert(TGoods record);

    int insertSelective(TGoods record);

    TGoods selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TGoods record);

    int updateByPrimaryKey(TGoods record);


    /**
     * 获取全部商品
     * @return
     */
    List<GoodsVo> findGoodsVo();


    /**
     * 根据商品id获取商品详情
     * @param goodsId
     * @return
     */
    GoodsVo findGoodsVoByGoodsId(Long goodsId);
}
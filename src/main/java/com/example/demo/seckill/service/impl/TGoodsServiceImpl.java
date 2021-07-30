package com.example.demo.seckill.service.impl;

import com.example.demo.seckill.bean.GoodsVo;
import com.example.demo.seckill.dao.TGoodsDao;
import com.example.demo.seckill.service.TGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TGoodsServiceImpl implements TGoodsService {



    @Autowired
    private TGoodsDao tGoodsDao;


    /**
     * 获取全部的商品对象
     *
     * @return
     */
    @Override
    public List<GoodsVo> findGoodsVo() {

        return tGoodsDao.findGoodsVo();
    }

    /**
     * 获取商品详情
     *
     * @param goodsId
     * @return
     */
    @Override
    public GoodsVo findGoodsVoByGoodsId(Long goodsId) {
        return tGoodsDao.findGoodsVoByGoodsId(goodsId);
    }
}

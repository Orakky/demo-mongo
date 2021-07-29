package com.example.demo.seckill.bean;

import java.math.BigDecimal;
import java.util.Date;

public class GoodsVo extends TGoods{

    /**
     * 秒杀价格
     */
    private BigDecimal seckillPrice;

    /**
     * 库存数量
     */
    private Integer stockCount;

    /**
     * 开始时间
     */
    private Date startDate;

    /**
     * 结束时间
     */
    private Date endDate;

    public GoodsVo() {
    }

    public GoodsVo(BigDecimal seckillPrice, Integer stockCount, Date startDate, Date endDate) {
        this.seckillPrice = seckillPrice;
        this.stockCount = stockCount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public BigDecimal getSeckillPrice() {
        return seckillPrice;
    }

    public void setSeckillPrice(BigDecimal seckillPrice) {
        this.seckillPrice = seckillPrice;
    }

    public Integer getStockCount() {
        return stockCount;
    }

    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}

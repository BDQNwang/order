package com.bdqn.order.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public class Order {
    private Integer orderId;

    private Integer userId;

    private Integer goodsId;

    private BigDecimal orderPrice;

    private Integer buyCount;

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date orderTime;


    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date overTime;

    private String orderStatus;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(this.orderTime);
        rightNow.add(Calendar.MINUTE,+45);//日期减1年
        this.overTime=rightNow.getTime();
    }

    public Date getOverTime() {
        return overTime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus == null ? null : orderStatus.trim();
    }
}
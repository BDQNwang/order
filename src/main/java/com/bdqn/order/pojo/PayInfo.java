package com.bdqn.order.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class PayInfo {
    private Integer payId;

    private Integer userId;

    private Integer orderId;

    private BigDecimal payAmt;

    private BigDecimal payBefore;

    private BigDecimal payAfter;

    private Date payTime;

    public Integer getPayId() {
        return payId;
    }

    public void setPayId(Integer payId) {
        this.payId = payId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getPayAmt() {
        return payAmt;
    }

    public void setPayAmt(BigDecimal payAmt) {
        this.payAmt = payAmt;
    }

    public BigDecimal getPayBefore() {
        return payBefore;
    }

    public void setPayBefore(BigDecimal payBefore) {
        this.payBefore = payBefore;
    }

    public BigDecimal getPayAfter() {
        return payAfter;
    }

    public void setPayAfter(BigDecimal payAfter) {
        this.payAfter = payAfter;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }
}
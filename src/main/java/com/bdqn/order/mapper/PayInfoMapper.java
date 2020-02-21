package com.bdqn.order.mapper;

import com.bdqn.order.pojo.PayInfo;

public interface PayInfoMapper {
    int deleteByPrimaryKey(Integer payId);

    int insert(PayInfo record);

    int insertSelective(PayInfo record);

    PayInfo selectByPrimaryKey(Integer payId);

    int updateByPrimaryKeySelective(PayInfo record);

    int updateByPrimaryKey(PayInfo record);
}
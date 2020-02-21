package com.bdqn.order.mapper;

import com.bdqn.order.pojo.Pay;

public interface PayMapper {
    int deleteByPrimaryKey(Integer payId);

    int insert(Pay record);

    int insertSelective(Pay record);

    Pay selectByPrimaryKey(Integer payId);

    int updateByPrimaryKeySelective(Pay record);

    int updateByPrimaryKey(Pay record);
}
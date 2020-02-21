package com.bdqn.order.mapper;

import com.bdqn.order.pojo.Goods;

import java.util.List;

public interface GoodsMapper {
    int deleteByPrimaryKey(Integer goodsId);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer goodsId);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

     List<Goods> selectGoodsList(Goods goods);

     int selectGoodsCount(Goods goods);
}
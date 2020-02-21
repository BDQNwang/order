package com.bdqn.order.mapper;

import com.bdqn.order.pojo.GoodsInfo;

import java.util.List;

public interface GoodsInfoMapper {
    int deleteByPrimaryKey(Integer goodsId);

    int insert(GoodsInfo record);

    int insertSelective(GoodsInfo record);

    GoodsInfo selectByPrimaryKey(Integer goodsId);

    int updateByPrimaryKeySelective(GoodsInfo record);

    int updateByPrimaryKeyWithBLOBs(GoodsInfo record);

    int updateByPrimaryKey(GoodsInfo record);

    List<GoodsInfo> selectGoodsList(GoodsInfo goodsInfo);

    int selectGoodsCount(GoodsInfo goodsInfo);

    int updateGoodsStock(String goodsId, Integer goodsCount);
}
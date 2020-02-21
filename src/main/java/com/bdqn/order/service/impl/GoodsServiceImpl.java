package com.bdqn.order.service.impl;

import com.bdqn.order.mapper.GoodsInfoMapper;
import com.bdqn.order.mapper.GoodsMapper;
import com.bdqn.order.pojo.Goods;
import com.bdqn.order.pojo.GoodsInfo;
import com.bdqn.order.service.GoodsService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private GoodsMapper goodsMapper;

    @Resource
    private GoodsInfoMapper goodsInfoMapper;
    @Override
    public List<Goods> getGoodsList(Goods goods) {
        //查询所有商品
        PageHelper.startPage(goods.getPageNum(),goods.getPageSize());
        return goodsMapper.selectGoodsList(goods);
    }

    /**
     * 查询商品列表
     * @param goodsInfo
     * @return
     */
    @Override
    public Map getGoodsList(GoodsInfo goodsInfo) {
        Map<String,Object> map=new HashMap<>();
        //查询所有商品
        PageHelper.startPage(goodsInfo.getPageNum(),goodsInfo.getPageSize());
        map.put("goodsList",goodsInfoMapper.selectGoodsList(goodsInfo)) ;
        //查询商品总数
        map.put("total",goodsInfoMapper.selectGoodsCount(goodsInfo));
        return map;
    }

    /**
     * 修改库存
     * @param goodsId
     * @param count
     * @return
     */
    @Override
    public int updateGoodsStock(String goodsId, Integer count) {
        return goodsInfoMapper.updateGoodsStock(goodsId,count);
    }

    @Override
    public GoodsInfo getGoodsById(Integer id) {
        return goodsInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int getAllCount(Goods goods) {
        return goodsMapper.selectGoodsCount(goods);
    }
}

package com.bdqn.order.service.impl;

import com.bdqn.order.mapper.OrderMapper;
import com.bdqn.order.pojo.GoodsInfo;
import com.bdqn.order.pojo.Order;
import com.bdqn.order.pojo.UserInfo;
import com.bdqn.order.service.GoodsService;
import com.bdqn.order.service.OrderService;
import com.bdqn.order.util.RedisUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private GoodsService goodsService;

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private RedisUtil redisUtil;
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Map addOrder(Order order) {
        Map<String,Object> map=new HashMap<>();
        map.put("retCode","500");
        map.put("retMsg","系统错误，请稍后在操作");
        //商品库存的校验
        int result=goodsService.updateGoodsStock(order.getGoodsId()+"",order.getBuyCount());
        if(result<=0){
            map.put("retCode","801");
            map.put("retMsg","商品已售空，请商品补充后在购买。");
            return map;
        }

        GoodsInfo goods=goodsService.getGoodsById(order.getGoodsId());
        //从shiro中取出用户数据
       UserInfo userInfo=(UserInfo)SecurityUtils.getSubject().getPrincipal();
        Order order1=new Order();
        order1.setGoodsId(order.getGoodsId());
        order1.setOrderPrice(goods.getGoodsPrice());
        order1.setUserId(userInfo.getUserId());
        order1.setOrderStatus("未支付");

        Boolean getLock=redisUtil.getLock(order.getGoodsId()+"_addOrder",10*1000);
        while(!getLock){
            System.out.println("当前商品已有人再购买，请稍后！");
            try{
                Thread.currentThread().sleep(1000);
            }catch (InterruptedException e){

            }
            getLock=redisUtil.getLock(order.getGoodsId()+"_addOrder",10*1000);
        }
        if(getLock){
            System.out.println("获得锁，继续执行");
        }
        //商品添加到订单
        if (orderMapper.insertSelective(order1)>0){
            map.put("retCode","1000");
            map.put("retMsg","下单成功");
            return map;
        }
        return map;
    }

    //商品库存的扣减

    @Override
    public List<Order> selectOrderByUserId(Integer id) {
        return orderMapper.selectAllOrder(id);
    }

    @Override
    public Order selectOrderById(Integer id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateOrder(Order order) {
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public int cancelOrder() {
        return orderMapper.cancelOrder();
    }
}

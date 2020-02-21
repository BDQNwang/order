package com.bdqn.order.controller;

import com.bdqn.order.pojo.*;
import com.bdqn.order.service.GoodsService;
import com.bdqn.order.service.LoginService;
import com.bdqn.order.service.OrderService;
import com.bdqn.order.service.PayService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/order/product")
public class GoodsController {

    @Resource
    private GoodsService goodsService;

    @Resource
    private OrderService orderService;

    @Resource
    private LoginService loginService;

    @Resource
    private PayService payService;

    @RequestMapping("/list")
    public Object selectAllGoods(GoodsInfo goodsInfo){
        Map map =goodsService.getGoodsList(goodsInfo);
        return map;
    }

    @GetMapping(value = "/{id}")
    public Object getInfo(@PathVariable Integer id)
    {
        GoodsInfo goods=goodsService.getGoodsById(id);
        Map map=new HashMap();
        map.put("info",goods.getGoodsInfo());
        return map;
    }

    @RequestMapping(value = "/addOrder/{id}")
    public Object addOrder(@PathVariable Integer goodsId,HttpSession session){
        Order order=new Order();
        order.setGoodsId(goodsId);
       return orderService.addOrder(order);
    }

    @RequestMapping("/allOrder")
    public Object selectAllOrder(HttpSession session){
        Map<String,Object> map=new HashMap<>();
        Integer id=(Integer) session.getAttribute("loginUser");
        List<Order> orderList=orderService.selectOrderByUserId(id);
        map.put("orderList",orderList);
        return map;
    }

    @RequestMapping(value = "/pay/{id}")
    @Transactional(propagation = Propagation.REQUIRED)
    public Object payOrder(@PathVariable Integer id,HttpSession session){
        Map<String,Object> map=new HashMap<>();
        Order order=orderService.selectOrderById(id);
        Integer userId=(Integer)session.getAttribute("loginUser");
        User user=loginService.checkLoginUser(userId);
        if(order.getOrderStatus().equals("已超时")){
            map.put("error","该订单超时，已取消");
            return map;
        }
        if(order.getOrderStatus().equals("已支付")){
            map.put("error","你的订单已支付，无需支付");
            return map;
        }
        if(user.getUserBalance().longValue()<order.getOrderPrice().longValue()){
            map.put("error","您的余额已不足，尚不能支付");
            return map;
        }
        Pay pay=new Pay();
        pay.setOrderId(id);
        pay.setUserId(userId);
        pay.setPayBefore(user.getUserBalance());
        BigDecimal bigDecimal=new BigDecimal(user.getUserBalance().longValue()-order.getOrderPrice().longValue());
        user.setUserBalance(bigDecimal);
        loginService.reduceBalance(user);
        pay.setPayAfter(user.getUserBalance());
        if(payService.addPayInfo(pay)>0){
            order.setOrderStatus("已支付");
            if(orderService.updateOrder(order)>0){
                map.put("result","success");
            }else{
                map.put("error","error");
            }
        }
        return map;
    }
}

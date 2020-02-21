package com.bdqn.order.util.quarzt;

import com.bdqn.order.service.OrderService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PayJob implements Job {

    @Resource
    private OrderService orderService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        int num=orderService.cancelOrder();
        if(num>0){
            System.out.println("成功取消订单一份");
        }else{
            System.out.println("暂无超时订单");
        }
    }
}

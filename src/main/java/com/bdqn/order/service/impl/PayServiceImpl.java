package com.bdqn.order.service.impl;

import com.bdqn.order.mapper.PayMapper;
import com.bdqn.order.pojo.Pay;
import com.bdqn.order.service.PayService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PayServiceImpl implements PayService {

    @Resource
    private PayMapper payMapper;
    @Override
    public int addPayInfo(Pay pay) {
        return payMapper.insertSelective(pay);
    }
}

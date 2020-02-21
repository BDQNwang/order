package com.bdqn.order.service.impl;

import com.bdqn.order.mapper.UserMapper;
import com.bdqn.order.pojo.User;
import com.bdqn.order.service.LoginService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private UserMapper userMapper;
    @Override
    public User loginUser(User user) {
        //登录方法实现
        return userMapper.selectUserByName(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int registerUser(User user) {
        //注册方法实现
        return userMapper.insertSelective(user);
    }

    @Override
    public User checkLoginUser(Integer id){
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int reduceBalance(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }


}

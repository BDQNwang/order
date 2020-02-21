package com.bdqn.order.service;

import com.bdqn.order.pojo.User;

/**
 * 登录service
 */
public interface LoginService {
    //登录功能
    public User loginUser(User user);

    /**
     * 注册功能
     * @param user
     * @return
     */
    public int registerUser(User user);

    /**
     * 检查是否登录
     * @param id
     * @return
     */
    public User checkLoginUser(Integer id);

    /**
     * 减少用户余额
     * @param user
     * @return
     */
    public int reduceBalance(User user);

}

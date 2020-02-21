package com.bdqn.order.service;

import com.bdqn.order.pojo.UserInfo;

import java.util.Map;


/**
 * 用户登录
 */
public interface UserService {

    public Map doLogin(UserInfo userInfo);

    public UserInfo getUserInfo(String UserName);
}

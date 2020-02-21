package com.bdqn.order.util;

import com.bdqn.order.pojo.User;
import com.bdqn.order.pojo.UserInfo;
import com.bdqn.order.service.LoginService;
import com.bdqn.order.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

/**
 * 自定义Realm
 */
public class UserRealm extends AuthorizingRealm {

    @Resource
    private LoginService loginService;

    @Resource
    private UserService userService;

    /**
     * 执行授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        return null;
    }

    /**
     * 执行认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token=(UsernamePasswordToken) authenticationToken;
        User user=new User();
        user.setUserName(token.getUsername());
        //判断用户是否存在
        UserInfo loginUser=userService.getUserInfo(token.getUsername());
        if(loginUser==null){
            return null;
        }
        //判断密码
        return new SimpleAuthenticationInfo(loginUser,loginUser.getUserPwd(),"");
    }
}

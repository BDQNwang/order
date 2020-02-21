package com.bdqn.order.service.impl;

import com.bdqn.order.mapper.UserInfoMapper;
import com.bdqn.order.pojo.UserInfo;
import com.bdqn.order.service.UserService;
import com.bdqn.order.util.Md5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public Map doLogin(UserInfo userInfo) {
        Map map=new HashMap();
        map.put("retCode","1000");
        map.put("retMsg","登录成功");

        Subject subject= SecurityUtils.getSubject();
        try {
            //此方法shiro会自动帮你去找用户名和密码自己做验证
            subject.login(new UsernamePasswordToken(userInfo.getUserName(), Md5Utils.hash(userInfo.getUserPwd())));
            map.put("user",subject.getPrincipal());
        }catch (UnknownAccountException un){
            map.put("retCode","901");
            map.put("retMsg","用户不存在");
        }catch (IncorrectCredentialsException in){
            map.put("retCode","902");
            map.put("retMsg","密码错误");
        }
        return map;
    }

    /**
     * 获取用户名
     * @param UserName
     * @return
     */
    @Override
    public UserInfo getUserInfo(String UserName) {
        return userInfoMapper.selectByName(UserName);
    }
}

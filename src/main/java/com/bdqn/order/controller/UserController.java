package com.bdqn.order.controller;

import com.bdqn.order.pojo.UserInfo;
import com.bdqn.order.service.UserService;
import com.bdqn.order.service.impl.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/login")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping
    public Map doLogin(@RequestBody UserInfo userInfo, HttpSession session){

        //1 调用service去登陆的校验
         Map retMap=userService.doLogin(userInfo);
        //2 如果登录成功，则将用户信息存入seesion
        if(retMap.get("retCode").equals("1000")){
            session.setAttribute("user",retMap.get("user"));
        }
            return retMap;
    }

}

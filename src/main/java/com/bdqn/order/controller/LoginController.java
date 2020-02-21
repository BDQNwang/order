package com.bdqn.order.controller;

import com.bdqn.order.pojo.User;
import com.bdqn.order.service.LoginService;
import com.bdqn.order.util.Md5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/order")
public class LoginController {

    @Resource
    private LoginService loginService;

    /**
     * 登录操作
     * @param user
     * @return
     */
    @RequestMapping("/login")
    public Object login(@RequestBody User user,HttpSession session)
    {
        Map<String,Object> map=new HashMap<>();
        if(user.getUserPwd()==null||user.getUserName()==null){
            map.put("error","请完善信息");
            return map;
        }
        Subject subject= SecurityUtils.getSubject();
        user.setUserPwd(Md5Utils.hash(user.getUserPwd()));
        UsernamePasswordToken token=new UsernamePasswordToken(user.getUserName(),user.getUserPwd());
        try {
            subject.login(token);
        }catch (UnknownAccountException un){
            map.put("error","用户不存在");
            return map;
        }catch (IncorrectCredentialsException in){
            map.put("error","密码错误");
            return map;
        }
        User user_ = (User) SecurityUtils.getSubject().getPrincipal();
        session.setAttribute("loginUser",user_.getUserId());
            map.put("result","true");
        return map;
    }

    @RequestMapping("/register")
    @Transactional(propagation = Propagation.REQUIRED)
    public Object register(@RequestBody User user)
    {
        Map map=new HashMap();
        if(user.getUserPwd()==null||user.getUserName()==null){
            map.put("error","请完善信息");
            return map;
        }
        User loginUser=loginService.loginUser(user);
        if(loginUser!=null){
            map.put("error","用户已存在");
            return map;
        }
        user.setUserBalance(BigDecimal.valueOf(1000));
        user.setUserPwd(Md5Utils.hash(user.getUserPwd()));
        if(loginService.registerUser(user)>0){
            map.put("success","true");
        }else{
            map.put("error","注册失败");
        }
        return map;
    }

    @RequestMapping("/toLogin")
    public Object toLogin(){
        Map map=new HashMap();
        map.put("error","null");
        return map;
    }
}

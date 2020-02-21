package com.bdqn.order.util;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro配置类
 */
@Configuration
public class ShiroConfig {

    /**
     * 创建Realm
     * @return
     */
    @Bean(name = "userRealm")
    public UserRealm getUserReaml(){
        return new UserRealm();
    }

    /**
     * 创建DefaultWebSecurityManager
     * @param userRealm
     * @return
     */
    @Bean(name = "defaultWebSecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /**
     * 创建ShiroFilterFactoryBean
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //添加shiro内置过滤器
        /**
         * Shiro内置过滤器，可以实现权限相关的拦截
         *      常用的过滤器有：
         *          anon：无需认证（登录）就可以访问
         *          authc：必须认证才能够访问
         *          user：如果使用rememberMe功能可以直接访问
         *          perms：该资源必须得到资源权限才可以访问
         *          role：该资源必须得到角色权限才可以访问
         */

        Map<String,String> filterMap=new LinkedHashMap<String, String>();
        filterMap.put("/order/login","anon");
        filterMap.put("/order/register","anon");
        filterMap.put("/order/toLogin","anon");
        filterMap.put("/order/product/list","anon");//product下面所有的路径都要过滤
        filterMap.put("/order/product/addOrder/**","authc");
        filterMap.put("/order/product/allOrder","authc");
        filterMap.put("/order/product/pay/**","authc");
        shiroFilterFactoryBean.setLoginUrl("/order/toLogin");
        shiroFilterFactoryBean.setUnauthorizedUrl("/order/toLogin");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }
}

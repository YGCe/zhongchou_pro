package com.yg.j1902.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.yg.j1902.realms.MyRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019/6/11.
 */
@Configuration
public class ShiroConfig {
    /*
       配置thymeleaf和shiro配合
       使用shiro：haspersmission()包裹动态控制页面菜单
   */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securitymanager") SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> fp = new LinkedHashMap<String, String>();
        /*
        * anon无需登录直接访问
        * authc必须登录才能访问
        * user如果使用remeberMe的功能可以直接访问
        * perms 该资源必须得到资源权限才能访问
        * role 必须得到角色
        * */
        // 配置不会被拦截的链接 顺序判断
        fp.put("/bootstrap/**", "anon");
        fp.put("/api/**", "anon");
        fp.put("/css/**", "anon");
        fp.put("/fonts/**", "anon");
        fp.put("/images/**", "anon");
        fp.put("/img/**", "anon");
        fp.put("/jquery/**", "anon");
        fp.put("/script/**", "anon");
        fp.put("/ztree/**", "anon");
        fp.put("/index", "anon");
        fp.put("/member", "anon");
        fp.put("/reg", "anon");
        fp.put("/regmember", "anon");
        fp.put("/", "anon");
        fp.put("/**", "authc");
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setFilterChainDefinitionMap(fp);
        //  shiroFilterFactoryBean.setSuccessUrl("/main");
        //未授权界面;
        //shiroFilterFactoryBean.setUnauthorizedUrl("/error");
        return shiroFilterFactoryBean;
    }

    @Bean(name = "securitymanager")
    public SecurityManager securityManager(@Qualifier("myrealm") MyRealm myRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm);
        return securityManager;
    }

    @Bean(name = "myrealm")
    public MyRealm myRealm(@Qualifier("matcher") HashedCredentialsMatcher matcher) {
        MyRealm myRealm = new MyRealm();
        myRealm.setCredentialsMatcher(matcher);
        return myRealm;
    }
    //设置凭证匹配器
    @Bean(name = "matcher")
    public HashedCredentialsMatcher matcher() {
        HashedCredentialsMatcher m = new HashedCredentialsMatcher();
        m.setHashAlgorithmName("MD5");
        m.setHashIterations(1024);
        m.setStoredCredentialsHexEncoded(true);
        return m;
    }

    //开启注解模式
    @Bean
    public DefaultAdvisorAutoProxyCreator advisocreat() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        return proxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor sourceAdvisor(@Qualifier("securitymanager") SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor sourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        sourceAdvisor.setSecurityManager(securityManager);
        return sourceAdvisor;
    }
}

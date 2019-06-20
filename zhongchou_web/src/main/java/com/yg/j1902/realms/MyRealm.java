package com.yg.j1902.realms;

import com.yg.j1902.service.UserService;
import com.yg.j1902.service.impl.UserServiceImpl;
import com.yg.j1902.vo.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2019/6/6.
 */

public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        String uname = (String) principalCollection.getPrimaryPrincipal();
        User user = userService.findUserByName(uname);
        String role = user.getUtype();
        info.addRole(role);
        info.addStringPermission(String.valueOf(user.getShimin()));
        return info;
    }


    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String uname = (String) authenticationToken.getPrincipal();
        User user = userService.findUserByName(uname);
        if (user == null) {
            throw new UnknownAccountException();
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(uname, user.getUpw(), getName());
        return simpleAuthenticationInfo;
    }
}

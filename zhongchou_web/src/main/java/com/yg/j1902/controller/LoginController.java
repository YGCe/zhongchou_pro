package com.yg.j1902.controller;

import com.yg.j1902.service.UserService;
import com.yg.j1902.vo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2019/5/27.
 */
@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/main")
    public String main() {
        return "main";
    }

    @RequestMapping(value = "member")
    public String member(User user, Model model, HttpSession session) {
        String s = new Md5Hash(user.getUpw(),null,1024).toString();
        Subject curr_user = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUname(), user.getUpw());
        try {
            curr_user.login(token);
            User user1 = userService.findUserByName(user.getUname());
            if (curr_user.isAuthenticated()) {
                session.setAttribute("id", user1.getId());
                session.setAttribute("shimin", user1.getShimin());
                session.setAttribute("uname", user1.getUname());
                if ("user".equals(user1.getUtype())) {
                    return "/member";
                } else {
                    return "/main";
                }
            }
        } catch (AuthenticationException e) {
            model.addAttribute("errstring", "用户名或密码错误");
            return "login";
        }
        return "login";
    }
}

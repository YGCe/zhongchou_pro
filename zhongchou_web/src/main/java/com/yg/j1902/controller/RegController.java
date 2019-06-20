package com.yg.j1902.controller;

import com.alibaba.druid.sql.ast.statement.SQLIfStatement;
import com.yg.j1902.service.UserService;
import com.yg.j1902.vo.User;
import org.apache.shiro.crypto.hash.Md5Hash;
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
public class RegController {

    @Autowired
    private UserService userService;

    @RequestMapping("/reg")
    public String reg() {
        return "reg";
    }

    @RequestMapping(value = "regmember", method = RequestMethod.POST)
    public String member(User user, Model model,HttpSession session) {
        String uname = user.getUname();
        if (uname != null && uname != "") {

            String upw = new Md5Hash(user.getUpw(), null, 1024).toString();
            user.setUpw(upw);
            user.setShimin(0);
            boolean result = userService.addUser(user);
            if (result) {
               return "/login";
            }
            model.addAttribute("errstring", "用户名已存在");
            return "reg";
        }else{
            model.addAttribute("errstring", "用户名不能为空");
            return "reg";
        }
    }
}

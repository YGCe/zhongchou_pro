package com.yg.j1902.controller;

import com.yg.j1902.service.UserService;
import com.yg.j1902.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2019/5/28.
 */
@Controller
public class UserController {


    @Autowired
    private UserService userService;

    @RequestMapping(value = "adduser", method = RequestMethod.POST)
    public String member(User user, Model model) {
        String uname = user.getUname();
        if (uname != null && uname != "") {
            user.setUpw("1234");
            user.setShimin(0);
            boolean result = userService.addUser(user);
            if (result) {
                return "redirect:user";
            }
            model.addAttribute("errstring", "用户名已存在");
            return "add";
        } else {
            model.addAttribute("errstring", "用户名不能为空");
            return "add";
        }
    }

    @RequestMapping("/edit{uid}")
    public String edit(@PathVariable("uid") String uid, Model model) {
//        String uid = request.getParameter("uid");
        System.out.println(uid);
//        int id=Integer.parseInt(uid);
//        User user = userService.findUserById(id);
//        model.addAttribute("user", user);
        return "edit";
    }

    @RequestMapping(value = "editjg")
    public String editjg(User user) {
        userService.updateUser(user);

        return "redirt:edit";
    }
}

package com.yg.j1902.controller;

import com.yg.j1902.service.UserInfoService;
import com.yg.j1902.service.UserService;
import com.yg.j1902.vo.User;
import com.yg.j1902.vo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2019/5/27.
 */
@Controller
public class MainController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserInfoService userInfoService;
    @RequestMapping(value = "user", method = RequestMethod.GET)
    public String user(Model model) {
        List<User> users = userService.findUsers();
        model.addAttribute("user",users);
        return "user";
    }
    @RequestMapping(value = "add",method = RequestMethod.GET)
    public String  addUser() {
        return "add";
    }


}

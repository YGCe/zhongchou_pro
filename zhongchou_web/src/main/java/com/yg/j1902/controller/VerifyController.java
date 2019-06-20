package com.yg.j1902.controller;

import com.yg.j1902.service.UserService;
import com.yg.j1902.vo.User;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2019/5/31.
 */

@Controller
@RequiresRoles("admin")
public class VerifyController {


    @Autowired
    private UserService userService;

    @RequestMapping("auth_cert")
    public String auth_cert(Model model) {

        List<User> users = userService.findUsersByShiMin();
        model.addAttribute("users",users);
        return "auth_cert";
    }

    @RequestMapping("auth_cert_1")
    @ResponseBody
    public void auth_cert_1(@RequestParam(value = "id",defaultValue = "0")int id,int sign) {
        if (sign==1){//通过
            userService.setShiMinOk(id);
        }else {//不通过
            userService.setShiMinX(id);
        }
    }

}

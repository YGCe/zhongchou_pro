package com.yg.j1902.controller;


import com.yg.j1902.Vvo.LoginVo;
import com.yg.j1902.Vvo.VGood;
import com.yg.j1902.service.TagGoodService;
import com.yg.j1902.service.UserService;
import com.yg.j1902.utils.ResultFactory;
import com.yg.j1902.vo.MsgResult;
import com.yg.j1902.vo.TagGood;
import com.yg.j1902.vo.User;
import com.yg.j1902.vo.UserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class VueController {
    @Autowired
    private UserService userService;
    @Autowired
    private TagGoodService tagGoodService;

    @RequestMapping(value = "/vreg", method = RequestMethod.POST)
    public String vreg(@RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "请求错误，请重新提交";
        }
        String uname = user.getUname();
        if (uname != null && uname != "") {
            String upw = new Md5Hash(user.getUpw(), null, 1024).toString();
            user.setUpw(upw);
            user.setShimin(0);
            boolean result = userService.addUser(user);
            if (result) {
                return "注册成功";
            }
            return "注册失败，请重新输入";
        } else {
            return "用户名不能为空";
        }
    }

    @RequestMapping(value = "/vgoods/type", method = RequestMethod.GET)
    public List<VGood> vggods() {
        List<TagGood> tagGoods = tagGoodService.findAll();
        List<VGood> vGoods = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        tagGoods.forEach(s -> {
            if (map.containsKey(s.getTagName())) {
                int integer = map.get(s.getTagName());
                map.put(s.getTagName(), integer + 1);
            } else {
                map.put(s.getTagName(), 1);
            }
        });
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        Iterator<Map.Entry<String, Integer>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            VGood vGood = new VGood();
            Map.Entry<String, Integer> next = iterator.next();
            vGood.setName(next.getKey());
            vGood.setValue(next.getValue());
            vGoods.add(vGood);
        }
        return vGoods;
    }

    @RequestMapping(value = "/vusers/bar")
    public int[] vusers_bar() {
        List<User> users = userService.findUsers();
        int[] ints = new int[4];
        users.forEach(s -> {
            if (s.getShimin() == 0) {
                ints[0]++;
            } else if (s.getShimin() == 1) {
                ints[1]++;
            } else if (s.getShimin() == 2) {
                ints[2]++;
            } else {
                ints[3]++;
            }
        });
        return ints;
    }

    @RequestMapping(value = "/vuser", method = RequestMethod.GET)
    public User vuser(@RequestParam(value = "uname", defaultValue = "") String uname) {
        User userByName = userService.findUserByName(uname);
        return userByName;
    }

    @RequestMapping(value = "/vlogin", method = RequestMethod.POST)
    public MsgResult vindex(@RequestBody LoginVo loginVo, BindingResult bindingResult) {
        System.out.println(loginVo);
        if (loginVo == null) {
            return ResultFactory.bulidFailResult("用户密码不能为空");
        }
        Subject login_sub = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginVo.getLoginName(), loginVo.getPassword());
        try {
            login_sub.login(token);
            if (login_sub.isAuthenticated()) {
                return ResultFactory.buildSucessResult("登录成功");
            }
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        if (bindingResult.hasErrors()) {
            return ResultFactory.bulidFailResult("请求错误");
        }
        return ResultFactory.bulidFailResult("用户名或者密码错误");
    }


    @CrossOrigin//跨域请求处理
    @RequestMapping(value = "/logins", method = RequestMethod.POST)
    public MsgResult getusers(@RequestBody UserVo userVo, BindingResult bindingResult) {

        System.out.println(userVo);
        if (bindingResult.hasErrors()) {//请求封装异常
            return ResultFactory.bulidFailResult("请求错误");
        }
        //用户名和密码错误
        if (userVo.getUname().equals("admin") || userVo.getUpw() != "admin") {
            return ResultFactory.bulidFailResult("用户名或者密码错误");
        }

        return ResultFactory.buildSucessResult("登录成功");
    }
}

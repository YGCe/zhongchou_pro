package com.yg.j1902.controller;

import com.yg.j1902.service.*;
import com.yg.j1902.utils.PageBean;
import com.yg.j1902.vo.*;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by Administrator on 2019/5/29.
 */

@Controller
@RequiresRoles(value = {"user"})
public class GoodController {


    @Autowired
    private ProjectTypeService projectTypeService;
    @Autowired
    private TagService tagService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private GoodService goodService;
    @Autowired
    private UserSelfService userSelfService;
    @Autowired
    private TagGoodService tagGoodService;
    @Autowired
    private RetService retService;

    @RequestMapping("/minecrowdfunding")
    public String minecrowdfunding(Model model, HttpSession session,
                                   @RequestParam(value = "currentPage", defaultValue = "1") int currentPage) {

        /*List<Good> goods = goodService.findGoods();*/
        PageBean<Good> pageBean = goodService.findGoodsByIterm(currentPage, 5);
        List<Good> goods = pageBean.getItems();
        model.addAttribute("goods", goods);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPage", pageBean.getTotalPage());

        int id = (int) session.getAttribute("id");
        List<Good> goods1 = new ArrayList<>();
        goods.forEach((s) -> {
            if (s.getGid() == id) {
                goods1.add(s);
            }
        });
        model.addAttribute("goods1", goods1);
        return "minecrowdfunding";
    }


    @RequestMapping("start")
    @RequiresPermissions(value = {"2"})
    public String start() {
        return "/start";
    }

    @RequiresPermissions("2")
    @RequestMapping("start-step-1")
    public String start_step_1(Model model, HttpSession session) {
        List<ProjectType> projectTypes = projectTypeService.findAll();
        List<Tag> tags = tagService.findAll();
        int id = (int) session.getAttribute("id");
        String tel = userInfoService.getTelById(id);
        model.addAttribute("tel", tel);
        model.addAttribute("projectTyppes", projectTypes);
        model.addAttribute("tags", tags);

        return "start-step-1";
    }

    @RequiresPermissions(value = {"2"})
    @RequestMapping("start-step-2")
    public String step_1_1(HttpServletRequest request,
                           @RequestParam(value = "file1") CommonsMultipartFile file1,
                           @RequestParam(value = "file2") CommonsMultipartFile file2,
                           Good good, @RequestParam("tags") String tags,
                           UserSelf userSelf,
                           String tel, HttpSession session) throws Exception {
        ServletContext servletContext = request.getServletContext();
        String realPath = "D:\\myjava\\zhongchou_pro\\zhongchou_web\\src\\main\\resources\\static\\images";
        OutputStream out;
        InputStream in;
        String fileName = new Date().getTime() + file1.getOriginalFilename();
        out = new FileOutputStream(new File(realPath + "\\" + fileName));
        in = file1.getInputStream();
        IOUtils.copy(in, out);
        good.setGheadimg("images/" + fileName);
        fileName = new Date().getTime() + file2.getOriginalFilename();
        out = new FileOutputStream(new File(realPath + "\\" + fileName));
        in = file1.getInputStream();
        IOUtils.copy(in, out);
        good.setGimg("images/" + fileName);
        out.close();
        in.close();
        goodService.add(good);
/*    good*/
        String[] strings = tags.split(",");
        List<TagGood> list = new ArrayList<>();
        for (String tag : strings) {
            TagGood tagGood = new TagGood();
            tagGood.setGname(good.getGname());
            tagGood.setTagName(tag);
            list.add(tagGood);
        }
        tagGoodService.add(list);
        //userself
        String uname = (String) session.getAttribute("uname");
        userSelf.setUserName(uname);
        userSelfService.add(userSelf);
        return "start-step-2";
    }

    @RequiresPermissions(value = {"2"})
    @RequestMapping("/start-step-2-1")
    @ResponseBody
    private void start_step_2_1(HttpServletRequest request,
                                @RequestParam(value = "rnimg1") MultipartFile file,
                                Ret return1,
                                @RequestParam(value = "xiangou", defaultValue = "0") String xiangou,
                                HttpSession session) throws Exception {
        ServletContext servletContext = request.getServletContext();
        String realPath = "D:\\myjava\\zhongchou_pro\\zhongchou_web\\src\\main\\resources\\static\\images";
        OutputStream out;
        InputStream in;
        String fileName = new Date().getTime() + file.getOriginalFilename();
        out = new FileOutputStream(new File(realPath + "\\" + fileName));
        in = file.getInputStream();
        IOUtils.copy(in, out);
        return1.setRnimg("images/" + fileName);
        if ("限购".equals(return1.getLimit())) {
            return1.setLimit(xiangou);
        }
        out.close();
        in.close();
        int id = (int) session.getAttribute("id");
        return1.setGid(id);
        System.out.println(return1);
        retService.add(return1);
    }

    @RequiresPermissions(value = {"2"})
    @RequestMapping("/start-step-3")
    public String start_step_3(Model model, HttpSession session) {
        int id = (int) session.getAttribute("id");
        String uname = (String) session.getAttribute("uname");
        String idCard = userInfoService.getIdCard(id);
        model.addAttribute("idcard", idCard);
        model.addAttribute("uname", uname);
        return "start-step-3";
    }

    @RequiresPermissions(value = {"2"})
    @RequestMapping("/start-step-4")
    public String start_step_4(@RequestParam(value = "banknum", defaultValue = "0") String banknum,
                               Model model, HttpSession session) {
        int id = (int) session.getAttribute("id");
        goodService.addBankNum(id, banknum);
        return "start-step-4";
    }
}

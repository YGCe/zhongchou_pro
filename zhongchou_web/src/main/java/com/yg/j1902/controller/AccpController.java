package com.yg.j1902.controller;

import com.yg.j1902.service.UserInfoService;
import com.yg.j1902.service.UserService;
import com.yg.j1902.vo.UserInfo;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Date;

/**
 * Created by Administrator on 2019/5/28.
 */
@Controller
public class AccpController {

    @Autowired
    UserService userService;
    @Autowired
    ServletContext servletContext;
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private SimpleMailMessage simpleMailMessage;

    @RequestMapping(value = "/apply")
    public String accp(String utype, HttpSession session) {
        String uname = (String) session.getAttribute("uname");
        int id = (int) session.getAttribute("id");
        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        userInfo.setUtype(utype);
        userInfoService.addUtype(userInfo);
        return "apply";
    }

    @RequestMapping(value = "/apply-1")
    public String appli_1(UserInfo userInfo, HttpSession session) {
        int id = (int) session.getAttribute("id");
        userInfo.setId(id);
        userInfoService.addUtype(userInfo);
        return "apply-1";
    }


    @RequestMapping(value = "/apply-2", method = RequestMethod.GET)
    public String accp12() {
        return "apply-1";
    }


    @RequestMapping(value = "/apply-2", method = RequestMethod.POST)
    public String testUpload(HttpServletRequest request,
                             @RequestParam(value = "file") CommonsMultipartFile file,
                             HttpSession session,
                             Model model) throws Exception {
        ServletContext servletContext = request.getServletContext();
        String realPath = "D:\\myjava\\zhongchou_pro\\zhongchou_web\\src\\main\\resources\\static\\images";
        OutputStream out;
        InputStream in;
        String fileName = new Date().getTime()+file.getOriginalFilename();
        System.out.println(fileName);
        out = new FileOutputStream(new File(realPath + "\\" + fileName));
        in = file.getInputStream();
        IOUtils.copy(in, out);
        out.close();
        in.close();

        int id = (int) session.getAttribute("id");
        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        userInfo.setImg("images/" + fileName);
        userInfoService.addUtype(userInfo);
        String email = userService.getEmailById(id);
        model.addAttribute("email", email);

        return "apply-2";
    }

    @RequestMapping(value = "apply-3", method = RequestMethod.POST)

    public String sendMail(@RequestParam(value = "email" ,defaultValue = "") String email,
                           HttpSession session) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper;
            messageHelper = new MimeMessageHelper(message, true, "UTF-8");
            messageHelper.setFrom(StringUtils.trimAllWhitespace(simpleMailMessage.getFrom()));
            messageHelper.setTo(email);//接收者
            messageHelper.setSubject("千锋众筹实名验证");//主题
            int sign=(int)((Math.random()*9+1)*100000);
            session.setAttribute("sign",sign);
            messageHelper.setText("验证码："+sign, true);//正文
            messageHelper.setSentDate(new Date());
            // 发送邮件
            javaMailSender.send(messageHelper.getMimeMessage());
        } catch (javax.mail.MessagingException e) {
            e.printStackTrace();
        }

        return "apply-3";
    }
    @RequestMapping(value = "apply-4" ,method = RequestMethod.POST)
    public String sign(@RequestParam(value = "sign",defaultValue = "")int sign,HttpSession session,Model model) {
        int sign1 = (int) session.getAttribute("sign");
        int id =(int) session.getAttribute("id");
        if (sign==sign1){
            userService.setShiMinWait(id);
            session.setAttribute("shimin",1);
            return "member";
        }
        model.addAttribute("errstring","验证码错误");
        return "apply-3";
    }



    @RequestMapping(value = "/accttype")
    public String accttype() {
        return "accttype";
    }

}

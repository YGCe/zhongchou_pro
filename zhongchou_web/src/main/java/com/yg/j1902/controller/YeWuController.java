package com.yg.j1902.controller;

import com.yg.j1902.service.CertService;
import com.yg.j1902.service.CertUtypeService;
import com.yg.j1902.vo.Cert;
import com.yg.j1902.vo.CertUtype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/5/29.
 */

@Controller
public class YeWuController {


    @Autowired
    private CertService certService;
    @Autowired
    private CertUtypeService certUtypeService;

    @RequestMapping(value = "cert")
    public String cert(Model model) {
        List<Cert> certs = certService.findAll();
        model.addAttribute("certs", certs);
        return "cert";

    }


    @RequestMapping(value = "type")
    public String type(Model model) {
        List<Cert> certs = certService.findAll();
        model.addAttribute("certs", certs);
        List<CertUtype> certUtypes= certUtypeService.findAll();
        model.addAttribute("certUtypes",certUtypes);
        return "type";

    }
    @RequestMapping(value = "/type-2",method = RequestMethod.POST)
    @ResponseBody
    public void type_2(@RequestParam("checkID[]") List<String> checkID) {
        List<CertUtype> list=new ArrayList<>();
        checkID.forEach((s)->{
            String[] strings = s.split("-");
            int a=Integer.parseInt(strings[0]);
            int b=Integer.parseInt(strings[1]);
            String certName=certService.findName(a);
            String utype ="";
           switch (b){
               case 1:
                   utype ="商业公司";
                   break;
               case 2:
                   utype ="个体工商户";
                   break;
               case 3:
                   utype ="个人经营";
                   break;
               case 4:
                   utype ="政府及非营利组织";
                   break;
           }
           CertUtype cert=new CertUtype();
           cert.setCertname(certName);
           cert.setUtype(utype);
           list.add(cert);
        });
        certUtypeService.clean();
        certUtypeService.add(list);
        /*return "a";*/
    }
}

package com.yg.j1902.controller;

import com.yg.j1902.service.ProjectTypeService;
import com.yg.j1902.vo.ProjectType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Administrator on 2019/5/27.
 */
@Controller
public class ProjectTypeController {

    @Autowired
    private ProjectTypeService pe;

    @RequestMapping(value = "project_type",method = RequestMethod.GET)
    public  String  project_type(Model model){
        List<ProjectType> list= pe.findAll();
        model.addAttribute("types",list);
        return "project_type";
    }
}

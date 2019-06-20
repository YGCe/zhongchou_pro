package com.yg.j1902.controller;

import com.yg.j1902.service.TagService;
import com.yg.j1902.vo.Tag;
import com.yg.j1902.vo.TagName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/5/27.
 */
@Controller
public class TagController {
    @Autowired
    private TagService tagService;

    @RequestMapping(value = "tags", method = RequestMethod.GET)
    public String findAll(Model model) {
        List<Tag> list = tagService.findAll();
        String s = typeListToWiredJson(list);

        model.addAttribute("tags", s);
        return "tag";
    }

    public static String typeListToWiredJson(List<Tag> types) {
        StringBuilder sb = new StringBuilder();
        sb.append("[{" +
                "\"id\": 1, \"pid\": 0, \"seqno\": 0, \"name\": \"众筹平台项目标签\", \"url\": null," +
                "\"icon\": \"glyphicon glyphicon-tags\", \"open\": true, \"checked\": false, \"children\": [");
        types.forEach((type) -> {
            sb.append("{\"id\": " + type.getId() + ", \"pid\": 1, \"seqno\": 0, \"name\": \""
                    + type.getTgType()
                    + "\", \"url\": \"dashboard.htm\", \"icon\": \"glyphicon glyphicon-th-large\", \"open\": true, \"checked\": false, \"children\": [");
            List<TagName> tips = type.getTgNames();
            tips.forEach((tip) -> {
                sb.append("{\"id\": " + tip.getTgid() + ", \"pid\": 7, \"seqno\": 1, \"name\": \""
                        + tip.getTgName()
                        + "\", \"url\": \"user/index.htm\", \"icon\": \"glyphicon glyphicon-tag\", \"open\": true, \"checked\": false, \"children\": [] },");
            });
            if (sb.lastIndexOf(",") == sb.length() - 1) {
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append("]},");
        });
        if (sb.lastIndexOf(",") == sb.length() - 1) {
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append("]}]");
        return sb.toString();
    }

}

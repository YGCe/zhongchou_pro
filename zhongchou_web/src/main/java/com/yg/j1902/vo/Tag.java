package com.yg.j1902.vo;

import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2019/5/27.
 */
@Data
public class Tag {


    private int id;
    private String tgType;
    private List<TagName> tgNames;

}

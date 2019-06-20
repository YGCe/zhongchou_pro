package com.yg.j1902.mapper;

import com.yg.j1902.vo.TagGood;

import java.util.List;

/**
 * Created by Administrator on 2019/5/30.
 */
public interface TagGoodMapper {
    void add(List<TagGood> list);

    List<TagGood> findAll();
}

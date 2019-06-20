package com.yg.j1902.service.impl;

import com.yg.j1902.mapper.TagGoodMapper;
import com.yg.j1902.service.TagGoodService;
import com.yg.j1902.vo.TagGood;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2019/5/30.
 */
@Service
public class TagGoodServiceImpl implements TagGoodService {

    @Resource
    private TagGoodMapper tagGoodMapper;

    @Override
    public void add(List<TagGood> list) {
        tagGoodMapper.add(list);
    }

    @Override
    public List<TagGood> findAll() {

       return tagGoodMapper.findAll();
    }
}

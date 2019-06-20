package com.yg.j1902.service.impl;

import com.yg.j1902.mapper.TagMapper;
import com.yg.j1902.service.TagService;
import com.yg.j1902.vo.Tag;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2019/5/27.
 */

@Service
public class TagServiceImpl implements TagService {

    @Resource
    private TagMapper tagMapper;

    @Override
    public List<Tag> findAll() {

        return tagMapper.findAll();
    }
}

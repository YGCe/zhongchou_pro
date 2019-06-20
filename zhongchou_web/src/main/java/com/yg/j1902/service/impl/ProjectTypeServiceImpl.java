package com.yg.j1902.service.impl;

import com.yg.j1902.mapper.ProjectTypeMapper;
import com.yg.j1902.service.ProjectTypeService;
import com.yg.j1902.vo.ProjectType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2019/5/27.
 */
@Service
public class ProjectTypeServiceImpl implements ProjectTypeService {

    @Resource
    private ProjectTypeMapper projectTypeMapper;


    @Override
    public List<ProjectType> findAll() {
        return projectTypeMapper.findAll();
    }
}

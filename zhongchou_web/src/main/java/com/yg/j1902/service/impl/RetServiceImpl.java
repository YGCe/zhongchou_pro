package com.yg.j1902.service.impl;

import com.yg.j1902.mapper.RetMapper;
import com.yg.j1902.service.RetService;
import com.yg.j1902.vo.Ret;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2019/5/31.
 */
@Service
public class RetServiceImpl implements RetService {
    @Resource
    private RetMapper retMapper;

    @Override
    public void add(Ret ret) {
        retMapper.add(ret);
    }
}

package com.yg.j1902.service.impl;

import com.yg.j1902.mapper.CertUtypeMapper;
import com.yg.j1902.service.CertUtypeService;
import com.yg.j1902.vo.CertUtype;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by Administrator on 2019/5/29.
 */
@Service
public class CertUtypeServiceImpl implements CertUtypeService {

    @Resource
    private CertUtypeMapper certUtypeMapper;

    @Override
    public void add(List<CertUtype> list) {
        certUtypeMapper.add(list);
    }

    @Override
    public void clean() {
        certUtypeMapper.clean();
    }

    @Override
    public List<CertUtype> findAll() {
        return certUtypeMapper.findAll();
    }
}

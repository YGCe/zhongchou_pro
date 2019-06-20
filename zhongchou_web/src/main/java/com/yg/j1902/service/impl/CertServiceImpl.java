package com.yg.j1902.service.impl;

import com.yg.j1902.mapper.CertMapper;
import com.yg.j1902.service.CertService;
import com.yg.j1902.vo.Cert;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2019/5/29.
 */
@Service
public class
CertServiceImpl implements CertService {

   @Resource
   private CertMapper certMapper;
    @Override
    public List<Cert> findAll() {

        return certMapper.findAll();
    }

    @Override
    public String findName(int a) {
        return certMapper.findName(a);
    }
}

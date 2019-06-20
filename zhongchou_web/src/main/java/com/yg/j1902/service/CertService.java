package com.yg.j1902.service;

import com.yg.j1902.vo.Cert;

import java.util.List;

/**
 * Created by Administrator on 2019/5/29.
 */
public interface CertService {
    List<Cert> findAll();

    String findName(int a);
}

package com.yg.j1902.mapper;

import com.yg.j1902.vo.CertUtype;

import java.util.List;

/**
 * Created by Administrator on 2019/5/29.
 */
public interface CertUtypeMapper {
    public void add(List<CertUtype> list);

    void clean();

    List<CertUtype> findAll();

}

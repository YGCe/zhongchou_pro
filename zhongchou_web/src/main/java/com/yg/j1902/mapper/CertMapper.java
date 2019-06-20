package com.yg.j1902.mapper;

import com.yg.j1902.vo.Cert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2019/5/29.
 */
public interface CertMapper {
    List<Cert> findAll();

    String findName(@Param("a") int a);
}

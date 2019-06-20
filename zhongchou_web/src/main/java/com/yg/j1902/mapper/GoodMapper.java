package com.yg.j1902.mapper;

import com.yg.j1902.vo.Good;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2019/5/30.
 */
public interface GoodMapper {
    public void add(Good good) ;

    void addBankNum(@Param("id") int id, @Param("banknum") String banknum);

    List<Good> findGoods();

    int countItem();
}

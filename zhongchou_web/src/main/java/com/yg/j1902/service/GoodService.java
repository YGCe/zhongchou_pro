package com.yg.j1902.service;

import com.yg.j1902.utils.PageBean;
import com.yg.j1902.vo.Good;

import java.util.List;

/**
 * Created by Administrator on 2019/5/30.
 */
public interface GoodService {
    void add(Good good);

    void addBankNum(int id, String banknum);

    List<Good> findGoods();

    PageBean<Good> findGoodsByIterm(int currentPage, int pageSize);
}

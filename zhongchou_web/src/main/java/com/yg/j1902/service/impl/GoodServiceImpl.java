package com.yg.j1902.service.impl;

import com.github.pagehelper.PageHelper;
import com.yg.j1902.mapper.GoodMapper;
import com.yg.j1902.service.GoodService;
import com.yg.j1902.utils.PageBean;
import com.yg.j1902.vo.Good;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2019/5/30.
 */
@Service
public class GoodServiceImpl implements GoodService {
    @Resource
    private GoodMapper goodMapper;

    @Override
    public void add(Good good) {
        goodMapper.add(good);
    }

    @Override
    public void addBankNum(int id, String banknum) {
        goodMapper.addBankNum(id, banknum);
    }

    @Override
    public List<Good> findGoods() {
        return goodMapper.findGoods();
    }

    @Override
    public PageBean<Good> findGoodsByIterm(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        List<Good> goods = findGoods();
        int countItems = goodMapper.countItem();
        PageBean<Good> objectPageBean = new PageBean<>(currentPage, pageSize, countItems);
        objectPageBean.setItems(goods);
        return objectPageBean;
    }
}

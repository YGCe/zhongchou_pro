package com.yg.j1902.service.impl;

import com.yg.j1902.mapper.UserInfoMapper;
import com.yg.j1902.service.UserInfoService;
import com.yg.j1902.vo.UserInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2019/5/28.
 */

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    UserInfoMapper userInfoMapper;
    @Override
    public void addUtype(UserInfo userInfo) {
        userInfoMapper.addUtype(userInfo);
    }

    @Override
    public String getTelById(int id) {
        return userInfoMapper.getTelById(id);
    }

    @Override
    public String getIdCard(int id) {
        return userInfoMapper.getIdCard(id);
    }
}

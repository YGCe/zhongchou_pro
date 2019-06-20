package com.yg.j1902.service.impl;

import com.yg.j1902.mapper.UserSelfMapper;
import com.yg.j1902.service.UserSelfService;
import com.yg.j1902.vo.UserSelf;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2019/5/30.
 */
@Service
public class UserSelfServiceImpl implements UserSelfService {

    @Resource
    private UserSelfMapper userSelfMapper;

    @Override
    public void add(UserSelf userSelf) {
        userSelfMapper.add(userSelf);
    }
}

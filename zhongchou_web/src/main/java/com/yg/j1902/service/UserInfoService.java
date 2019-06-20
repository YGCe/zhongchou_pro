package com.yg.j1902.service;

import com.yg.j1902.vo.UserInfo;

/**
 * Created by Administrator on 2019/5/28.
 */
public interface UserInfoService {

    void addUtype(UserInfo userInfo);

    String getTelById(int id);

     String getIdCard(int id);
}

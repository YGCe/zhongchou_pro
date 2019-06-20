package com.yg.j1902.mapper;

import com.yg.j1902.vo.UserInfo;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2019/5/28.
 */
public interface UserInfoMapper {

    void addUtype(UserInfo userInfo);

    String getTelById(@Param("id") int id);

    String getIdCard(int id);
}

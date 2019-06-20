package com.yg.j1902.service;

import com.yg.j1902.vo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2019/5/27.
 */
public interface UserService {
    List<User> findUsers();
    boolean addUser(User user);

    boolean isOk(User user);

    int getIdByName(String uname);

    String getEmailById(int id);

    int getShiminByName(String uname);

    List<User> findUsersByShiMin();

    void setShiMinOk(int id);

    void setShiMinX(int id);

    User findUserById(int id);

    void updateUser(User user);

    void setShiMinWait(int id);

    String  findUpwByName(String uname);

    User findUserByName(@Param("uname") String uname);
}

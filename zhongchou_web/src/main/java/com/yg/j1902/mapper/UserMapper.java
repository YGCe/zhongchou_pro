package com.yg.j1902.mapper;

import com.yg.j1902.vo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2019/5/27.
 */
public interface UserMapper {

    List<User> findUsers();

    void addUser(User user);

    int getIdByName(@Param("uname") String uname);

    String getEmailById(@Param("id") int id);

    int getShiminByName(String uname);

    List<User> findUsersByShiMin();

    void setShiMinOk(@Param("id") int id);

    void setShiMinX(@Param("id") int id);

    User findUserById(@Param("id") int id);

    void updateUser(User user);

    void setShiMinWait(int id);

    String findUpwByName(String uname);

    User findUserByName(String uname);
}

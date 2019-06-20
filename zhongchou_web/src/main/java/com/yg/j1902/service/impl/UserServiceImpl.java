package com.yg.j1902.service.impl;

import com.yg.j1902.mapper.UserMapper;
import com.yg.j1902.service.UserService;
import com.yg.j1902.vo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2019/5/27.
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;


    @Override
    public List<User> findUsers() {
        return userMapper.findUsers();
    }

    @Override
    public boolean addUser(User user) {
        List<User> users = findUsers();
        for (User user1 : users) {
            if (user1.getUname().equals(user.getUname())) {
                return false;
            }
        }
        userMapper.addUser(user);
        return true;
    }

    @Override
    public boolean isOk(User user) {
        List<User> users = findUsers();
        for (User user1 : users) {
            if (user1.getUname().equals(user.getUname())) {
                if (user1.getUpw().equals((user.getUpw()))) {
                    if (user1.getUtype().equals((user.getUtype()))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public int getIdByName(String uname) {
        return userMapper.getIdByName(uname);
    }

    @Override
    public String getEmailById(int id) {
        return userMapper.getEmailById(id);
    }

    @Override
    public int getShiminByName(String uname) {
        return userMapper.getShiminByName(uname);
    }

    @Override
    public List<User> findUsersByShiMin() {
        return userMapper.findUsersByShiMin();
    }

    @Override
    public void setShiMinOk(int id) {
        userMapper.setShiMinOk(id);
    }

    @Override
    public void setShiMinX(int id) {
        userMapper.setShiMinX(id);
    }

    @Override
    public User findUserById(int id) {
        return userMapper.findUserById(id);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void setShiMinWait(int id) {
        userMapper.setShiMinWait(id);
    }

    @Override
    public String findUpwByName(String uname) {
        return userMapper.findUpwByName(uname);
    }

    @Override
    public User findUserByName(String uname) {
        return userMapper.findUserByName(uname) ;
    }
}

package com.lucien.service.impl;

import com.lucien.dao.UserDao;
import com.lucien.dao.impl.UserDaoImpl;
import com.lucien.domain.User;
import com.lucien.service.UserService;

import java.sql.SQLException;

public class UserServiceImpl implements UserService{

    @Override
    public void userRegist(User user) throws SQLException {
        UserDao userDao = new UserDaoImpl();
        userDao.userRegist(user);
    }

    @Override
    public User userLogin(User user) throws SQLException {
        UserDao userDao = new UserDaoImpl();
        User userResp = userDao.userLogin(user);
        if(userResp == null) {
            throw new RuntimeException("密码有误");
        }
        return userResp;
    }
}

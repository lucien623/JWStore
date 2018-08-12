package com.lucien.service;

import com.lucien.domain.User;

import java.sql.SQLException;

public interface UserService {
    /**
     * 注册用户
     * @param user
     */
    void userRegist(User user) throws SQLException;
}

package com.lucien.dao;

import com.lucien.domain.User;

import java.sql.SQLException;

public interface UserDao {

    void userRegist(User user) throws SQLException;
}

package com.lucien.dao;

import com.lucien.domain.User;
import org.omg.PortableInterceptor.USER_EXCEPTION;

import java.sql.SQLException;

public interface UserDao {

    void userRegist(User user) throws SQLException;

    User userLogin(User user) throws SQLException;
}

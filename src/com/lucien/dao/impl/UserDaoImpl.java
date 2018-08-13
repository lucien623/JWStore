package com.lucien.dao.impl;

import com.lucien.dao.UserDao;
import com.lucien.domain.User;
import com.lucien.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    @Override
    public void userRegist(User user) throws SQLException {
        String sql = "insert into user values(?,?,?,?,?,?,?,?,?,?);";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        queryRunner.update(sql, user.getUid(), user.getUsername(), user.getPassword(), user.getName(),
                user.getEmail(), user.getTelephone(), user.getBirthday(), user.getSex(), user.getState(), user.getCode());
    }

    @Override
    public User userLogin(User user) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from user where username = ? and password = ?";
        return  queryRunner.query(sql, new BeanHandler<User>(User.class), user.getUsername(), user.getPassword());
    }
}

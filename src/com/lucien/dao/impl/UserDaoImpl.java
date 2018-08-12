package com.lucien.dao.impl;

import com.lucien.dao.UserDao;
import com.lucien.domain.User;
import com.lucien.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    @Override
    public void userRegist(User user) throws SQLException {
        String sql = "insert into user values(?,?,?,?,?,?,?,?,?,?);";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        queryRunner.update(sql, user.getUid(), user.getUsername(), user.getPassword(), user.getName(),
                user.getEmail(), user.getTelephone(), user.getBirthday(), user.getSex(), user.getState(), user.getCode());
    }
}

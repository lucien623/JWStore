package com.lucien.dao.impl;

import com.lucien.dao.ProductDao;
import com.lucien.domain.PageModel;
import com.lucien.domain.Product;
import com.lucien.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    @Override
    public List<Product> findHots() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from product WHERE pflag = 0 and is_hot = 1 ORDER BY pdate DESC LIMIT 0, 9";
        return queryRunner.query(sql, new BeanListHandler<Product>(Product.class));
    }

    @Override
    public List<Product> findNews() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from product WHERE pflag = 0 ORDER BY pdate DESC LIMIT 0, 9";
        return queryRunner.query(sql, new BeanListHandler<Product>(Product.class));
    }

    @Override
    public Product findProductByPid(String pid) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from product where pid = ?";
        return queryRunner.query(sql, new BeanHandler<Product>(Product.class), pid);
    }

    @Override
    public int findTotalRecords(String cid) throws SQLException {
        String sql = "select count(*) from product where cid = ?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        Long num = (Long)queryRunner.query(sql, new ScalarHandler(), cid);
        return num.intValue();
    }

    @Override
    public List findProductsByCidWithPage(String cid, int startIndex, int pageSize) throws SQLException {
        String sql = "select * from product WHERE cid = ? LIMIT ?, ?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        return queryRunner.query(sql, new BeanListHandler<Product>(Product.class), cid, startIndex, pageSize);
    }
}

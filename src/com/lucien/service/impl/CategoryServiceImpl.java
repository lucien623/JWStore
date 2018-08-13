package com.lucien.service.impl;

import com.lucien.dao.CategoryDao;
import com.lucien.dao.impl.CategoryDaoImpl;
import com.lucien.domain.Category;
import com.lucien.service.CategoryService;

import java.sql.SQLException;
import java.util.List;

public class CategoryServiceImpl implements CategoryService{
    @Override
    public List<Category> getAllCats() throws SQLException {
        CategoryDao categoryDao = new CategoryDaoImpl();
        List<Category> list = categoryDao.getAllCats();
        return list;
    }
}

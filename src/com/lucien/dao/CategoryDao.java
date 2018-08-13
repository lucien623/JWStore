package com.lucien.dao;

import com.lucien.domain.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryDao {
    List<Category> getAllCats() throws SQLException;
}

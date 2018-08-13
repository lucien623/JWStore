package com.lucien.service.impl;

import com.lucien.dao.ProductDao;
import com.lucien.dao.impl.ProductDaoImpl;
import com.lucien.domain.PageModel;
import com.lucien.domain.Product;
import com.lucien.service.ProductService;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    @Override
    public List<Product> findHots() throws SQLException {
        ProductDao productDao = new ProductDaoImpl();
        List<Product> hots = productDao.findHots();
        return hots;
    }

    @Override
    public List<Product> findNews() throws SQLException {
        ProductDao productDao = new ProductDaoImpl();
        List<Product> news = productDao.findNews();
        return news;
    }

    @Override
    public Product findProductByPid(String pid) throws SQLException {
        ProductDao productDao = new ProductDaoImpl();
        Product product = productDao.findProductByPid(pid);
        return product;
    }

    @Override
    public PageModel findProductsByCidWithPage(String cid, int curNum) throws SQLException {
        ProductDao productDao = new ProductDaoImpl();
        int totalRecords = productDao.findTotalRecords(cid);
        PageModel pageModel = new PageModel(curNum, totalRecords, 12);
        List list = productDao.findProductsByCidWithPage(cid, pageModel.getStartIndex(), pageModel.getPageSize());
        pageModel.setList(list);
        pageModel.setUrl("ProductServlet?method=findProductsByCidWithPage&cid=" + cid);
        return pageModel;
    }
}

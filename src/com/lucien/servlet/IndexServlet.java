package com.lucien.servlet;

import com.lucien.base.BaseServlet;
import com.lucien.domain.Category;
import com.lucien.domain.Product;
import com.lucien.service.CategoryService;
import com.lucien.service.ProductService;
import com.lucien.service.impl.CategoryServiceImpl;
import com.lucien.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "IndexServlet", urlPatterns = "/IndexServlet")
public class IndexServlet extends BaseServlet {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        /*CategoryService categoryService = new CategoryServiceImpl();
        List<Category> list = null;
        try {
            list = categoryService.getAllCats();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("allCats", list);*/
        try {
            ProductService productService = new ProductServiceImpl();
            List<Product> hots = productService.findHots();
            List<Product> news = productService.findNews();
            req.setAttribute("hots", hots);
            req.setAttribute("news", news);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "/jsp/index.jsp";
    }
}

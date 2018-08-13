package com.lucien.servlet;

import com.lucien.base.BaseServlet;
import com.lucien.domain.PageModel;
import com.lucien.domain.Product;
import com.lucien.service.ProductService;
import com.lucien.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ProductServlet", urlPatterns = "/ProductServlet")
public class ProductServlet extends BaseServlet {

    public String findProductByPid(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String pid = req.getParameter("pid");
            ProductService productService = new ProductServiceImpl();
            Product product = productService.findProductByPid(pid);
            req.setAttribute("product", product);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "/jsp/product_info.jsp";
    }

    public String findProductsByCidWithPage(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        String cid = req.getParameter("cid");
        int num = Integer.parseInt(req.getParameter("num"));
        ProductService productService = new ProductServiceImpl();
        PageModel pageModel = productService.findProductsByCidWithPage(cid, num);
        req.setAttribute("page", pageModel);
        return "/jsp/product_list.jsp";
    }
}

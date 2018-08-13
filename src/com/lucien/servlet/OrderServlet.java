package com.lucien.servlet;

import com.lucien.base.BaseServlet;
import com.lucien.domain.*;
import com.lucien.util.UUIDUtils;
import com.sun.tools.corba.se.idl.constExpr.Or;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(name = "OrderServlet", urlPatterns = "/OrderServlet")
public class OrderServlet extends BaseServlet {

    /**
     * create an order
     * @param req
     * @param resp
     * @return
     */
    public String saveOrder(HttpServletRequest req, HttpServletResponse resp) {
        //check user login status
        User user = (User) req.getSession().getAttribute("loginUser");
        if(user == null) {
            req.setAttribute("msg", "请登录之后再下单");
            return "/jsp/info.jsp";
        }
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        Order order = new Order();
        order.setOid(UUIDUtils.getCode());
        order.setOrdertime(new Date());
        order.setTotal(cart.getTotal());
        order.setState(1);
        order.setUser(user);
        for(CartItem item: cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setItemid(UUIDUtils.getCode());
            orderItem.setQuantity(item.getNum());
            orderItem.setTotal(item.getSubTotal());
            orderItem.setProduct(item.getProduct());

            orderItem.setOrder(order);
            order.getList().add(orderItem);
        }
        cart.clearCart();

        return "";
    }
}

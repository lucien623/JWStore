package com.lucien.base;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.service(req, resp);
        String method = req.getParameter("method");
        Class clazz = this.getClass();
        try {
            Method md = clazz.getDeclaredMethod(method, HttpServletRequest.class, HttpServletResponse.class);
            String jspPath = (String) md.invoke(this, req, resp);
            if(jspPath != null) {
                req.getRequestDispatcher(jspPath).forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

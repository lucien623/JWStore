package com.lucien.servlet;

import com.lucien.base.BaseServlet;
import com.lucien.domain.User;
import com.lucien.service.UserService;
import com.lucien.service.impl.UserServiceImpl;
import com.lucien.util.MyBeanUtils;
import com.lucien.util.UUIDUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet(name = "UserServlet", urlPatterns = "/UserServlet")
public class UserServlet extends BaseServlet {

    public String registerUI(HttpServletRequest request, HttpServletResponse response) {
        return "/jsp/register.jsp";
    }

    /**
     * 注册用户 注册成功跳转至提示页面并向用户邮箱发送信息 失败则跳转至提示页面
     *
     * @param request
     * @param response
     * @return
     */
    public String userRegist(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException {
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        MyBeanUtils.populate(user, map);
        user.setUid(UUIDUtils.getId());
        user.setState(0);
        user.setCode(UUIDUtils.getCode());
        UserService userService = new UserServiceImpl();
        try {
            userService.userRegist(user);
//            MailUtils.sendMail(user.getEmail(), user.getUid());
            request.setAttribute("msg", "用户注册成功，请激活");
        } catch (Exception e) {
            request.setAttribute("msg", "用户注册失败");
        }
        return "/jsp/info.jsp";
    }

    public String loginUI(HttpServletRequest request, HttpServletResponse response) {
        return "/jsp/login.jsp";
    }

    public String userLogin(HttpServletRequest request, HttpServletResponse response) {
        User user = new User();
        MyBeanUtils.populate(user, request.getParameterMap());
        UserService userService = new UserServiceImpl();
        User userResp = null;
        try {
            userResp = userService.userLogin(user);
            request.getSession().setAttribute("loginUser", userResp);
            response.sendRedirect("/index.jsp");
            return null;
        } catch (Exception e) {
            String msg = e.getMessage();
            request.setAttribute("msg", msg);
            return "/jsp/login.jsp";
        }
    }

    public String loginOut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().invalidate();
        resp.sendRedirect("/index.jsp");
        return null;
    }
}

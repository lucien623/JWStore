package com.lucien.servlet;

import com.lucien.base.BaseServlet;
import com.lucien.domain.Category;
import com.lucien.service.CategoryService;
import com.lucien.service.impl.CategoryServiceImpl;
import com.lucien.util.JedisUtils;
import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "CategoryServlet", urlPatterns = "/CategoryServlet")
public class CategoryServlet extends BaseServlet {
    public String getAllCats(HttpServletRequest req, HttpServletResponse resp) {
        Jedis jedis = JedisUtils.getJedis();
        try {
            String jsonStr = jedis.get("allCats");
            if(jsonStr == null || jsonStr.equals("")) {
                CategoryService categoryService = new CategoryServiceImpl();
                List<Category> list = categoryService.getAllCats();
                jsonStr = JSONArray.fromObject(list).toString();
                jedis.set("allCats", jsonStr);
            }
            resp.setContentType("application/json;charset=utf-8");
            resp.getWriter().print(jsonStr);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            JedisUtils.closeJedis(jedis);
        }
        return null;
    }
}

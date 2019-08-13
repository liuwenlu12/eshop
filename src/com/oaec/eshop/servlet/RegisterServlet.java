package com.oaec.eshop.servlet;

import com.alibaba.fastjson.JSON;
import com.oaec.eshop.service.Impl.UserServiceImpl;
import com.oaec.eshop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/views/register.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("username");
        String password = req.getParameter("password");
        String telNumber = req.getParameter("telNumber");
        Map<String, Object> register = userService.register(name, password, telNumber);

        Map<String, Object> json = new HashMap<>();
        if (register.containsKey("success")) {
            json.put("result", true);
        } else {
            Object error = register.get("error");
            json.put("result", false);
            json.put("error", error);
        }
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println(JSON.toJSONString(json));
        writer.close();



    }
}

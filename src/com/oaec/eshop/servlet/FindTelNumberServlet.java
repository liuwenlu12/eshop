package com.oaec.eshop.servlet;


import com.oaec.eshop.service.Impl.UserServiceImpl;
import com.oaec.eshop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/findTelNumber")
public class FindTelNumberServlet extends HttpServlet {
private UserService userService = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String telNumber = req.getParameter("telNumber");
        Boolean tel = userService.getTelNumber(username,telNumber);
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("{\"success\":"+tel+"}");
        writer.close();
    }
}

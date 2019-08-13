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


@WebServlet("/updateForgetPassWord")
public class UpdateForgetPassWord extends HttpServlet {
private UserService userService = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Boolean forgetPassWord = userService.forgetPassWord(username, password);
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("{\"success\":"+forgetPassWord+"}");
        writer.close();
    }
}

package com.oaec.eshop.servlet;

import com.oaec.eshop.service.CartService;
import com.oaec.eshop.service.Impl.CartServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;


@WebServlet("/cart/delete")
public class DeleteCartServlet extends HttpServlet {
    private CartService cartService = new CartServiceImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Map<String,Object>  user =    (Map<String,Object>)session.getAttribute("user");
        int userId = Integer.parseInt(user.get("user_id").toString());
        String color = req.getParameter("color");
        int productId = Integer.parseInt(req.getParameter("productId"));

        boolean b = cartService.deleteCart(userId, color, productId);
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("{\"success\":"+b+"}");
        writer.close();
    }
}

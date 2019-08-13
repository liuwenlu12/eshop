package com.oaec.eshop.servlet;


import com.oaec.eshop.service.Impl.OrderServiceImpl;
import com.oaec.eshop.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet("/order/changeStatus")
public class ChangeOrderStatusServlet extends HttpServlet {
    private OrderService orderService = new OrderServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Map<String,Object> user =    (Map<String,Object>)session.getAttribute("user");
        int userId = Integer.parseInt(user.get("user_id").toString());
        String oId = req.getParameter("orderId");
        int orderId = Integer.parseInt(oId);
        boolean b = orderService.changeOrderStatus(userId, orderId);
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("{\"success\":"+b+"}");
        writer.close();

    }
}

package com.oaec.eshop.servlet;

import com.alibaba.fastjson.JSON;
import com.oaec.eshop.service.Impl.OrderServiceImpl;
import com.oaec.eshop.service.Impl.PersonalServiceImpl;
import com.oaec.eshop.service.OrderService;
import com.oaec.eshop.service.PersonalService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/orderDetail")
public class OrderDetailServlet extends HttpServlet {
    private OrderService orderService = new OrderServiceImpl();
    private PersonalService personalService = new PersonalServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");

        int userId = Integer.parseInt(user.get("user_id").toString());

        String oId = req.getParameter("orderId");
        int orderId = Integer.parseInt(oId);
        List<Map<String, Object>> orderDetailList = orderService.getOrderDetail(userId, orderId);
        Double orderDetailPrice = personalService.getOrderDetailPrice(orderId);

        req.setAttribute("orderDetailList",orderDetailList);
        req.setAttribute("orderDetailPrice",orderDetailPrice);
        req.getRequestDispatcher("WEB-INF/views/orderxq.jsp").forward(req,resp);

    }


}

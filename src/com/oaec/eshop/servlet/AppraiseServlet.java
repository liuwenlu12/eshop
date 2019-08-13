package com.oaec.eshop.servlet;

import com.alibaba.fastjson.JSON;
import com.oaec.eshop.service.Impl.OrderServiceImpl;
import com.oaec.eshop.service.Impl.ProductServiceImpl;
import com.oaec.eshop.service.OrderService;
import com.oaec.eshop.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;


@WebServlet("/appraise")
public class AppraiseServlet extends HttpServlet {
private ProductService productService = new ProductServiceImpl();
private OrderService orderService = new OrderServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String dId = req.getParameter("detailId");
        int detailId = Integer.parseInt(dId);
        Map<String, Object> detailMap = orderService.getDetailByDetailId(detailId);
        req.setAttribute("detailMap",detailMap);
        req.getRequestDispatcher("WEB-INF/views/myprod.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
        int userId = Integer.parseInt(user.get("user_id").toString());
        String dId = req.getParameter("detailId");
        int detailId = Integer.parseInt(dId);
        Map<String, Object> detailMap = orderService.getDetailByDetailId(detailId);
        int productId = Integer.parseInt(detailMap.get("product_id").toString());
        String content = req.getParameter("content");
        Boolean appraise = productService.Appraise(userId, content, productId, detailId);

        resp.setContentType("application/json;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("{\"success\":"+appraise+"}");
        writer.close();



    }
}

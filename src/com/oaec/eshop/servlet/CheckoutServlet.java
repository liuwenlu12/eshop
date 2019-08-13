package com.oaec.eshop.servlet;


import com.alibaba.fastjson.JSON;
import com.oaec.eshop.service.AddressService;
import com.oaec.eshop.service.CartService;
import com.oaec.eshop.service.Impl.AddressServiceImpl;
import com.oaec.eshop.service.Impl.CartServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    private CartService cartService = new CartServiceImpl();
    private AddressService addressService = new AddressServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");

        int userId = Integer.parseInt(user.get("user_id").toString());
        String[] cartIds = req.getParameterValues("cartId");
        List<Map<String, Object>> addressList = addressService.getAddress(userId);
        List<Map<String, Object>> productList = cartService.getProducts4Checkout(userId, cartIds);
        Map<String, Object> total = cartService.getTotal(userId, cartIds);

        req.setAttribute("total",total);
        req.setAttribute("addressList",addressList);
        req.setAttribute("productList",productList);
        req.getRequestDispatcher("WEB-INF/views/order.jsp").forward(req,resp);
    }
}

package com.oaec.eshop.servlet;

import com.alibaba.fastjson.JSON;
import com.oaec.eshop.service.CartService;
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


@WebServlet("/cart")
public class CartServlet extends HttpServlet {
private CartService cartService = new CartServiceImpl();
    
    
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
          Map<String,Object> user =  (Map<String,Object>) session.getAttribute("user");
        String user_id = user.get("user_id").toString();
        List<Map<String, Object>> products = cartService.getCartProducts(user_id);
        req.setAttribute("products",products);

        req.getRequestDispatcher("WEB-INF/views/cart.jsp").forward(req,resp);
    }
}

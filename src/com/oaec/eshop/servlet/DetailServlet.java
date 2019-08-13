package com.oaec.eshop.servlet;

import com.alibaba.fastjson.JSON;
import com.oaec.eshop.service.DetailService;
import com.oaec.eshop.service.Impl.DetailServiceImpl;
import com.oaec.eshop.service.Impl.ProductServiceImpl;

import com.oaec.eshop.service.ProductService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@WebServlet("/detail")
public class DetailServlet extends HttpServlet {
        private ProductService productService = new ProductServiceImpl();
        private DetailService detailService = new DetailServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String productId = req.getParameter("productId");


        Map<String, Object> detail = productService.getDetail(productId);
        List<Map<String, Object>> appraiseList = productService.getAppraise(productId);
        List<Map<String, Object>> productLick = detailService.getTypeLickProduct(productId);
        req.setAttribute("detail",detail);
        req.setAttribute("productLick",productLick);
        req.setAttribute("appraiseList",appraiseList);
        req.getRequestDispatcher("WEB-INF/views/proDetail.jsp").forward(req,resp);
    }
}

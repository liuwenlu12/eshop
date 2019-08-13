package com.oaec.eshop.servlet;

import com.oaec.eshop.dao.BrandDao;
import com.oaec.eshop.dao.Impl.BrandDaoImpl;
import com.oaec.eshop.service.Impl.NavServiceImpl;
import com.oaec.eshop.service.Impl.ProductServiceImpl;
import com.oaec.eshop.service.NavService;
import com.oaec.eshop.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@WebServlet("/index")
public class IndexServlet extends HttpServlet {

  // private NavService navService = new NavServiceImpl();
    private  ProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    //    List<Map<String, Object>> nav = navService.getNav();
        List<Map<String, Object>> hot = productService.queryHot();
        List<Map<String, Object>> newProduct = productService.queryNew();
        List<Map<String, Object>> lowPrice = productService.queryLowPrice();
        req.setAttribute("hot",hot);
        req.setAttribute("newProduct",newProduct);
        req.setAttribute("lowPrice",lowPrice);
     //   req.setAttribute("nav",nav);
        req.getRequestDispatcher("WEB-INF/views/index.jsp").forward(req,resp);
    }
}

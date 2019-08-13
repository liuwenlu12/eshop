package com.oaec.eshop.servlet;



import com.alibaba.fastjson.JSON;
import com.oaec.eshop.service.Impl.PropertyServiceImpl;
import com.oaec.eshop.service.PropertyService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


@WebServlet("/inventory")
public class InventoryServlet extends HttpServlet {
 private PropertyService propertyService = new PropertyServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String color = req.getParameter("color");
        String productId = req.getParameter("productId");
        Map<String, Object> inventory = propertyService.getInventory(color, productId);
        Map<Object, Object> map = new HashMap<>();
        map.put("inventory",inventory);
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println(JSON.toJSONString(map));
        writer.close();

    }
}

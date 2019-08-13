package com.oaec.eshop.servlet;


import com.oaec.eshop.service.AddressService;
import com.oaec.eshop.service.Impl.AddressServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet("/addAddress")
public class AddAddressServlet extends HttpServlet {
private AddressService addressService = new AddressServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Map<String,Object> user =  (Map<String,Object>) session.getAttribute("user");
        String user_id = user.get("user_id").toString();
        String address = req.getParameter("address");
        String name = req.getParameter("name");
        String telNumber = req.getParameter("telNumber");

        Boolean insert = addressService.doInsert(name,telNumber,address,user_id);

        resp.setContentType("application/json;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("{\"success\":"+insert+"}");
        writer.close();
    }
}

package com.oaec.eshop.servlet;

import com.oaec.eshop.service.Impl.PersonalServiceImpl;
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


@WebServlet("/address")
public class AddressServlet extends HttpServlet {
    private PersonalService personalService = new PersonalServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");

        int userId = Integer.parseInt(user.get("user_id").toString());


        List<Map<String, Object>> addressList = personalService.getAddress(userId);
        req.setAttribute("addressList",addressList);

        req.getRequestDispatcher("WEB-INF/views/address.jsp").forward(req,resp);

    }
}

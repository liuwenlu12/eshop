package com.oaec.eshop.servlet;


import com.alibaba.fastjson.JSON;
import com.oaec.eshop.service.Impl.UserServiceImpl;
import com.oaec.eshop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/updatePassWord")
public class UpdatePassWordServlet extends HttpServlet {

private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/views/remima.jsp").forward(req,resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
        int userId = Integer.parseInt(user.get("user_id").toString());

        String password = req.getParameter("password");
        String telNumber = req.getParameter("telNumber");
            String  newPassWord =  req.getParameter("newPassWord");
        Map<String, Object> updatePassWord = userService.updatePassWord(userId, password, telNumber,newPassWord);
        Map<String, Object> json = new HashMap<>();
        if(updatePassWord.containsKey("success")){
            json.put("result",true);
        }else{
            Object error = updatePassWord.get("error");
            json.put("result",false);
            json.put("error",error);
        }
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println(JSON.toJSONString(json));
        writer.close();
    }
}

package com.oaec.eshop.filter;

import com.oaec.eshop.util.HttpFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


@WebFilter({"/cart","/add2cart","/cart/delete","/updateAmount","/personalCenter","/checkout","/personalOrder","/submit","/ok","/order/changeStatus","/orderDetail","/updatePassWord","/appraise","/deleteAddress","/address","/updateAddress","/addAddress","/detail"})
public class LoginFilter extends HttpFilter {


    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = request.getSession();
       if(session.getAttribute("user")==null) {
           if(isAjax(request)){
               response.setContentType("application/json;charset=utf-8");
               PrintWriter writer = response.getWriter();
               writer.println("{\"isLogin\":false}");
               writer.close();
               return;
           }
           response.sendRedirect(request.getContextPath()+"/login");
           return;
       }
        filterChain.doFilter(request,response);
    }
    private boolean isAjax(HttpServletRequest request){
        String header = request.getHeader("X-Requested-With");
        if(header!=null && header.equals("XMLHttpRequest")){
            return true;
        }else{
            return false;
        }
    }

}

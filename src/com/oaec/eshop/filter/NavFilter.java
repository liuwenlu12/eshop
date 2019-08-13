package com.oaec.eshop.filter;

import com.oaec.eshop.service.Impl.NavServiceImpl;
import com.oaec.eshop.service.NavService;
import com.oaec.eshop.util.HttpFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@WebFilter({"/index","/list","/detail","/cart","/address","/personalCenter","/checkout","/personalOrder","/submit","/ok","/orderDetail","/updatePassWord","/appraise","/deleteAddress"})
public class NavFilter extends HttpFilter {

    private NavService navService = new NavServiceImpl();
    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        List<Map<String, Object>> nav = navService.getNav();
        request.setAttribute("nav",nav);
        filterChain.doFilter(request,response);
    }
}

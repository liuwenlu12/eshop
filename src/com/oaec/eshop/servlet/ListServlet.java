package com.oaec.eshop.servlet;

import com.oaec.eshop.dao.Impl.ProductDaoImpl;
import com.oaec.eshop.dao.ProductDao;
import com.oaec.eshop.service.BrandService;
import com.oaec.eshop.service.Impl.BrandServiceImpl;
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


@WebServlet("/list")
public class ListServlet extends HttpServlet {
    private ProductService productService = new ProductServiceImpl();
   // private NavService navService = new NavServiceImpl();
    private BrandService brandService = new BrandServiceImpl();
  
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String typeId = req.getParameter("typeId");
        String brandId = req.getParameter("brandId");
        String saleDesc = req.getParameter("saleDesc");
        String priceLow = req.getParameter("priceLow");
        String page = req.getParameter("page");

        if(page==null){
            page = "1";
        }
        req.setAttribute("page",page);
        int count = productService.getCount(name, typeId, brandId);
        double pageNum = Math.ceil((double) count / ProductDao.PAGE_SIZE);
        req.setAttribute("pageNum",pageNum);

        List<Map<String, Object>>  list = null;
        if(saleDesc!=null){
            list = productService.queryHot();
        }else{
            if(priceLow!=null){
                list=  productService.getLowPrice();
            }else{
                list = productService.query(name,typeId,brandId,page);
            }
        }



        req.setAttribute("list",list);
     //   List<Map<String, Object>> nav = navService.getNav();
     //   req.setAttribute("nav",nav);
        List<Map<String, Object>> brands = brandService.query(typeId);
        req.setAttribute("brands",brands);


        req.getRequestDispatcher("WEB-INF/views/zbproList.jsp").forward(req,resp);
    }
}

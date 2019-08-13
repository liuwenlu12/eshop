package com.oaec.eshop.test;

import com.alibaba.fastjson.JSON;
import com.oaec.eshop.dao.BrandDao;
import com.oaec.eshop.dao.CartDao;
import com.oaec.eshop.dao.Impl.BrandDaoImpl;
import com.oaec.eshop.dao.Impl.CartDaoImpl;
import com.oaec.eshop.dao.Impl.ProductDaoImpl;
import com.oaec.eshop.dao.Impl.TypeDaoImpl;
import com.oaec.eshop.dao.ProductDao;
import com.oaec.eshop.service.*;
import com.oaec.eshop.service.Impl.*;
import com.oaec.eshop.servlet.CheckoutServlet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hello {
    public static void main(String[] args) {
      /*  CartService cartService = new CartServiceImpl();
        Boolean b = cartService.isOverAmount(6, "白色", 4007, 6001);
        System.out.println("b = " + b);*/
        ProductDaoImpl productDao = new ProductDaoImpl();
        List<Map<String, Object>> maps = productDao.queryAll(1);
        for (Map<String, Object> map : maps) {
            System.out.println("map = " + map);
        }

    }
}


package com.oaec.eshop.service.Impl;

import com.oaec.eshop.dao.Impl.ProductDaoImpl;
import com.oaec.eshop.dao.ProductDao;
import com.oaec.eshop.service.DetailService;

import java.util.List;
import java.util.Map;

public class DetailServiceImpl implements DetailService {
    private ProductDao productDao = new ProductDaoImpl();

    @Override
    public List<Map<String, Object>> getTypeLickProduct(String productId) {
        return productDao.queryTypeByProductId(Integer.parseInt(productId));
    }
}

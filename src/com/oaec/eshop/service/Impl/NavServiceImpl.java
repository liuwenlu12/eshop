package com.oaec.eshop.service.Impl;

import com.oaec.eshop.dao.BrandDao;
import com.oaec.eshop.dao.Impl.BrandDaoImpl;
import com.oaec.eshop.dao.Impl.ProductDaoImpl;
import com.oaec.eshop.dao.Impl.TypeDaoImpl;
import com.oaec.eshop.dao.ProductDao;
import com.oaec.eshop.dao.TypeDao;
import com.oaec.eshop.service.NavService;

import java.util.List;
import java.util.Map;

public class NavServiceImpl implements NavService {
        private TypeDao typeDao = new TypeDaoImpl();
             private ProductDao productDao  =    new ProductDaoImpl();
             private BrandDao brandDao = new BrandDaoImpl();
    @Override
    public List<Map<String, Object>> getNav() {
        List<Map<String, Object>> typeMaps = typeDao.queryNav();
        for (Map<String, Object> typeMap : typeMaps) {
            int type_id = Integer.parseInt(typeMap.get("type_id").toString());
            List<Map<String, Object>> products = productDao.queryByTypeId(type_id);
            typeMap.put("products",products);
            List<Map<String, Object>> brands = brandDao.queryByTypeId(type_id);
            typeMap.put("brands",brands);
        }

        return  typeMaps;
    }
}

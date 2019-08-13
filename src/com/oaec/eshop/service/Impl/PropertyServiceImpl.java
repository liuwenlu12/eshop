package com.oaec.eshop.service.Impl;

import com.oaec.eshop.dao.Impl.PropertyDaoImpl;
import com.oaec.eshop.dao.PropertyDao;
import com.oaec.eshop.service.PropertyService;


import java.util.Map;

public class PropertyServiceImpl implements PropertyService {

    private PropertyDao propertyDao = new PropertyDaoImpl();
    @Override
    public Map<String, Object> getInventory(String color, String productId) {
        return propertyDao.queryByColorAndProductId(color,Integer.parseInt(productId));
    }
}

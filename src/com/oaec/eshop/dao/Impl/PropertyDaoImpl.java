package com.oaec.eshop.dao.Impl;


import com.oaec.eshop.dao.PropertyDao;
import com.oaec.eshop.util.CommonDao;

import java.util.List;
import java.util.Map;

public class PropertyDaoImpl extends CommonDao implements PropertyDao {
    @Override
    public List<Map<String, Object>> queryByProductId(Integer productId) {
        String sql = "SELECT * FROM property WHERE product_id = ?";
        return  query4MapList(sql,productId);
    }

    @Override
    public Map<String, Object> queryByColorAndProductId(String color, Integer productId) {
        String sql = "SELECT inventory FROM property WHERE color = ? AND product_id = ?";
        return query4Map(sql,color,productId);
    }
}

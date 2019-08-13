package com.oaec.eshop.dao.Impl;

import com.oaec.eshop.dao.BrandDao;
import com.oaec.eshop.util.CommonDao;

import java.util.List;
import java.util.Map;

public class BrandDaoImpl extends CommonDao implements BrandDao {
    @Override
    public List<Map<String, Object>> queryAll() {
        String sql = "SELECT * FROM brand WHERE brand_id IN (SELECT brand_id FROM product WHERE status = 1 GROUP BY brand_id)";
        return query4MapList(sql);
    }

 /*   @Override
    public List<Map<String, Object>> queryByTypeId(Integer typeId) {
        String sql = "SELECT brand.brand_id,brand.brand_name,product.type_id,product.product_name,product.img_url FROM product,brand WHERE product.brand_id = brand.brand_id AND type_id = ?";
        return query4MapList(sql,typeId);
    }*/

    @Override
    public Map<String, Object> queryByBrandId(Integer brandId) {
       String sql = "SELECT * FROM brand WHERE brand_id = ?";
       return query4Map(sql,brandId);
    }

    @Override
    public List<Map<String, Object>> queryByTypeId(Integer typeId) {
        String sql = "SELECT * FROM brand WHERE brand_id IN (SELECT brand_id FROM product WHERE type_id = ? AND status = 1 GROUP BY brand_id)";
        return query4MapList(sql,typeId);
    }




}

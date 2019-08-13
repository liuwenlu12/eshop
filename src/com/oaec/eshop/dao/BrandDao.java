package com.oaec.eshop.dao;

import java.util.List;
import java.util.Map;

public interface BrandDao {
    List<Map<String,Object>> queryAll();


    List<Map<String,Object>> queryByTypeId(Integer typeId);

    Map<String,Object> queryByBrandId(Integer brandId);
}

package com.oaec.eshop.dao;

import java.util.List;
import java.util.Map;

public interface PropertyDao {

    List<Map<String,Object>> queryByProductId(Integer productId);

    Map<String,Object> queryByColorAndProductId(String color,Integer productId);
 }

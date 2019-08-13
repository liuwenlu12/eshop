package com.oaec.eshop.dao;

import java.util.List;
import java.util.Map;

public interface CartDao {

    List<Map<String,Object>> queryByUserId(Integer userId);


    int doInsert(Map<String,Object> param);


    Map<String,Object> queryByUserIdANDProductIdANDColor(Map<String,Object> param);

    int  updateAmount(Map<String,Object> param);


    int doDelete(Map<String,Object> param);

    int addAmount(Map<String,Object> param);

    int subAmount(Map<String,Object> param);

   Map<String,Object> queryTotal(Integer userId,String[] cartIds);

    List<Map<String,Object>> queryProduct4Checkout(Integer userId,String[] cartIds);
    Map<String,Object> queryByUserIdANDProductId(Integer userId,Integer productId);
    int doDelete(Integer cartId);

    Map<String, Object> queryIsCartOverAmount(Integer cartId,String color,Integer productId);
}

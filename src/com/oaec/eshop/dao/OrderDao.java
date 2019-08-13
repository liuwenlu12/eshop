package com.oaec.eshop.dao;

import java.util.List;
import java.util.Map;

public interface OrderDao {

    List<Map<String,Object>> queryProductByOrderId(Integer orderId);
    List<Map<String,Object>> queryByUserId(Integer userId);
    Double getTotalPrice(Integer orderId);
    int doInsert(Integer userId,Integer addressId);

    int doInsertDetail(Map<String,Object> param);

    int doChangeStatus(Integer userId,Integer orderId);
    /*int doInsertOrderDetail(Map<String,Object> param);*/

    List<Map<String,Object>> queryByUserIdANDOrderId(Integer userId,Integer CartId);

    Map<String,Object> queryDetailByDetailId(Integer detailId);

    int doUpdateDetailStatus(Integer detailId);



}

package com.oaec.eshop.service;

import java.util.List;
import java.util.Map;

public interface OrderService {

    List<Map<String,Object>>  getOrders (Integer userId);

    boolean submit(Integer userId,Integer addressId, String[] productIds);

    boolean changeOrderStatus(Integer userId,Integer orderId);

    List<Map<String,Object>> getOrderDetail(Integer userId,Integer orderId);

    Map<String,Object> getDetailByDetailId(Integer detailId);
}

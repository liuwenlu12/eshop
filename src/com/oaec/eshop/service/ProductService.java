package com.oaec.eshop.service;

import java.util.List;
import java.util.Map;

public interface ProductService {

    List<Map<String,Object>> query(String name,String typeId,String brandId,String page);
    int getCount(String name,String typeId,String brandId);

    List<Map<String,Object>>  queryHot();

    Map<String,Object> getDetail(String productId);

    List<Map<String,Object>>  queryNew();

    List<Map<String,Object>> queryLowPrice();

    Boolean Appraise(Integer userId,String content,Integer productId,Integer detailId);

    List<Map<String,Object>> getAppraise(String productId);
    List<Map<String,Object>>  getLowPrice();
}

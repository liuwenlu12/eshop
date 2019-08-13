package com.oaec.eshop.dao;

import java.util.List;
import java.util.Map;

public interface ProductDao {

    int PAGE_SIZE = 4;

    List<Map<String,Object>> queryAll(int page);
    int getCountAll();


    List<Map<String,Object>> queryLike(String name);

    List<Map<String,Object>> queryByTypeId(Integer typeId);

    List<Map<String,Object>>   queryBySales();

    List<Map<String,Object>> queryByBrandId(Integer brandId);

    List<Map<String,Object>> queryByBrandIdAndTypeId(Integer brandId,Integer typeId);

    Map<String,Object> queryByProductId(Integer productId);

    List<Map<String,Object>> queryTypeByProductId(Integer productId);


    List<Map<String,Object>> queryByAddTime();

    List<Map<String,Object>> queryByPriceAndOldPrice();

    int updateInventoryAndSalesVolume(Integer productId,Integer amount,String color);

    int doInsertAppraise(Integer userId,String content,Integer productId,Integer detailId);

    List<Map<String,Object>> queryAppraiseByProductIdAndUserId(Integer productId,Integer userId);

    List<Map<String,Object>> queryByPrice();
}

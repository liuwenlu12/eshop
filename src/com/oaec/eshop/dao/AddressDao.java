package com.oaec.eshop.dao;

import java.util.List;
import java.util.Map;

public interface AddressDao {

    Map<String,Object> queryByAddressId(Integer addressId);

    List<Map<String,Object>> queryByUserId(Integer userId);

    int deleteByAddressIdAndUserId(Integer addressId,Integer userId);

    int updateAddressByUserIdAndAddressId(Integer userId,String address,String telNumber,String name,Integer addressId);

    int doInsert(String name,String telNumber,String address,Integer userId);
}

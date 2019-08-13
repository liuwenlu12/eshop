package com.oaec.eshop.service;

import java.util.List;
import java.util.Map;

public interface AddressService {

    List<Map<String,Object>> getAddress(Integer userId);


    Boolean doDelete(String addressId,String userId);

    Boolean doUpdate(String userId,String address ,String telNumber,String name,String addressId);

    Boolean doInsert(String name,String telNumber,String address,String userId);
}

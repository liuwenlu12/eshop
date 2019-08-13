package com.oaec.eshop.service;

import java.util.List;
import java.util.Map;

public interface PropertyService {

    Map<String,Object> getInventory(String color,String productId);

}

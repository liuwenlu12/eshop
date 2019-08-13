package com.oaec.eshop.service;

import java.util.List;
import java.util.Map;

public interface DetailService {

   List<Map<String,Object>> getTypeLickProduct(String productId);
}

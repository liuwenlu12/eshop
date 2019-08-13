package com.oaec.eshop.dao;

import java.util.List;
import java.util.Map;

public interface PictureDao {

    List<Map<String,Object>> queryByProductId(Integer productId);

}

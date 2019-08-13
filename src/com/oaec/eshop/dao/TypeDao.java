package com.oaec.eshop.dao;

import java.util.List;
import java.util.Map;

public interface TypeDao {

    List<Map<String,Object>> queryNav();

    Map<String,Object> queryByTypeId(Integer typeId);

}

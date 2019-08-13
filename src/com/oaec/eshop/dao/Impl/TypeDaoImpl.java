package com.oaec.eshop.dao.Impl;

import com.oaec.eshop.dao.TypeDao;
import com.oaec.eshop.util.CommonDao;

import java.util.List;
import java.util.Map;

public class TypeDaoImpl extends CommonDao implements TypeDao {
    @Override
    public List<Map<String, Object>> queryNav() {
        String  sql = "SELECT * FROM type WHERE type_id IN (SELECT type_id FROM product GROUP BY type_id)";
        return query4MapList(sql);
    }

    @Override
    public Map<String, Object> queryByTypeId(Integer typeId) {
        String sql = "SELECT * FROM type WHERE type_id = ?";
        return query4Map(sql,typeId);
    }
}

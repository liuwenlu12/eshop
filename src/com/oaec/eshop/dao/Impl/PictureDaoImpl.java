package com.oaec.eshop.dao.Impl;

import com.oaec.eshop.dao.PictureDao;
import com.oaec.eshop.util.CommonDao;

import java.util.List;
import java.util.Map;

public class PictureDaoImpl extends CommonDao implements PictureDao {
    @Override
    public List<Map<String, Object>> queryByProductId(Integer productId) {
        String sql = "SELECT * FROM picture WHERE product_id = ?";
        return query4MapList(sql,productId);
    }
}

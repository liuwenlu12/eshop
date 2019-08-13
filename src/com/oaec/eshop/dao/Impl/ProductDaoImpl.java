package com.oaec.eshop.dao.Impl;

import com.oaec.eshop.dao.ProductDao;
import com.oaec.eshop.util.CommonDao;

import java.util.List;
import java.util.Map;

public class ProductDaoImpl extends CommonDao implements ProductDao {
   /* @Override
    public List<Map<String, Object>> queryAll() {
        String sql = "SELECT * FROM product WHERE status = 1";
       return   query4MapList(sql);
    }*/

    @Override
    public List<Map<String, Object>> queryAll(int page) {
        String sql = "SELECT * FROM (SELECT ROWNUM rn,t1.* FROM(SELECT * FROM product WHERE status = 1 ) t1)t2 WHERE t2.rn > ? AND t2.rn <= ?";
        return query4MapList(sql,(page-1)*PAGE_SIZE,page*PAGE_SIZE);
    }

    @Override
    public int getCountAll() {
        String sql = "SELECT COUNT(PRODUCT_ID) COUNT FROM PRODUCT WHERE STATUS = 1";
        Map<String, Object> map = query4Map(sql);
        int count = Integer.parseInt(map.get("count").toString());
        return  count;


    }

    @Override
    public List<Map<String, Object>> queryLike(String name) {
        String sql = "SELECT * FROM product WHERE UPPER(product_name) LIKE UPPER(?) AND status = 1";
        return query4MapList(sql,"%"+name+"%");
    }

    @Override
    public List<Map<String, Object>> queryByTypeId(Integer typeId) {
        String sql = "SELECT * FROM product WHERE type_id = ? AND status = 1";
        return  query4MapList(sql,typeId);
    }

    @Override
    public List<Map<String, Object>> queryBySales() {
        String sql = "SELECT * FROM product WHERE status = 1 ORDER BY sales_volume DESC";
        return query4MapList(sql);
    }

    @Override
    public List<Map<String, Object>> queryByBrandId(Integer brandId) {
        String sql = "SELECT * FROM product WHERE brand_id = ? AND status = 1 ";
        return query4MapList(sql,brandId);
    }

    @Override
    public List<Map<String, Object>> queryByBrandIdAndTypeId(Integer brandId, Integer typeId) {
        String sql = "SELECT * FROM product WHERE brand_id = ? AND type_id = ? AND status = 1";
        return  query4MapList(sql,brandId,typeId);
    }

    @Override
    public Map<String, Object> queryByProductId(Integer productId) {
        String sql = "SELECT * FROM product WHERE product_id=?";
        return query4Map(sql,productId);
    }

    @Override
    public List<Map<String, Object>> queryTypeByProductId(Integer productId) {
        String sql ="SELECT * FROM product WHERE type_id = (SELECT type_id FROM product WHERE product_id = ?)";
        return  query4MapList(sql,productId);
    }

    @Override
    public List<Map<String, Object>> queryByAddTime() {
        String sql = "SELECT * FROM product WHERE status = 1 ORDER BY add_time DESC";
        return query4MapList(sql);
    }

    @Override
    public List<Map<String, Object>> queryByPriceAndOldPrice() {
        String sql = "SELECT * FROM product WHERE old_price - price >=500 ORDER BY old_price - price DESC";
        return query4MapList(sql);
    }

    @Override
    public int updateInventoryAndSalesVolume(Integer productId, Integer amount,String color) {
        String sql1="UPDATE property SET inventory = inventory - ? WHERE product_id = ? AND color = ?";
        executeUpdate(sql1,amount,productId,color);
       String sql = "UPDATE product SET sales_volume = sales_volume + ? WHERE product_id = ?";
       return executeUpdate(sql,amount,productId);
    }

    @Override
    public int doInsertAppraise(Integer userId,String content ,Integer productId,Integer detailId) {
            String sql1 = "SELECT * FROM appraise WHERE user_id = ? AND product_id = ?";
        Map<String, Object> appraiseMap = query4Map(sql1, userId, productId);
        if(appraiseMap==null){
            String sql = "INSERT INTO appraise VALUES(seq_appraise.nextval,?,?,?)";
            executeUpdate(sql,productId,userId,detailId);
        }
        String sql2 = "UPDATE order_detail SET content = ?,add_time = sysdate  WHERE detail_Id = ?";

        return executeUpdate(sql2,content,detailId);
    }

    @Override
    public List<Map<String, Object>> queryAppraiseByProductIdAndUserId(Integer productId,Integer userId) {
        String sql = "SELECT * FROM APPRAISE,ORDER_DETAIL,USERS WHERE APPRAISE.PRODUCT_ID = ORDER_DETAIL.PRODUCT_ID AND USERS.USER_ID = APPRAISE.USER_ID AND order_detail.USER_ID = APPRAISE.USER_ID AND APPRAISE.PRODUCT_ID = ?  AND users.user_id = ? AND ORDER_DETAIL.status = 1 ORDER BY ORDER_DETAIL.add_time DESC";
        return query4MapList(sql,productId,userId);
    }

    @Override
    public List<Map<String, Object>> queryByPrice() {
        String sql = "SELECT * FROM product WHERE status = 1 ORDER BY price  ";
        return query4MapList(sql);
    }
}

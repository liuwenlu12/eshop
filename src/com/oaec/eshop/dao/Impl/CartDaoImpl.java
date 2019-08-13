package com.oaec.eshop.dao.Impl;

import com.oaec.eshop.dao.CartDao;
import com.oaec.eshop.util.CommonDao;


import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CartDaoImpl extends CommonDao implements CartDao{
    @Override
    public List<Map<String, Object>> queryByUserId(Integer userId) {
       String sql = "SELECT * FROM cart t1,property t2,product t3 WHERE t1.property_id = t2.property_id AND t2.product_id = t3.product_id AND t1.user_id = ?";
       return  query4MapList(sql,userId);
    }

    @Override
    public int doInsert(Map<String, Object> param) {


        String sql1 = "SELECT property_id FROM property WHERE color = ? AND product_id = ?";
        Map<String, Object> property = query4Map(sql1, param.get("color"), param.get("productId"));

        String sql = "INSERT INTO cart VALUES(seq_cart.nextval,?,?,?,sysdate)";
        return  executeUpdate(sql,param.get("amount"),property.get("property_id"),param.get("userId"));
    }

    @Override
    public Map<String, Object> queryByUserIdANDProductIdANDColor(Map<String, Object> param) {
        String sql = "SELECT * FROM cart WHERE user_id = ? AND property_id =(SELECT property_id FROM property WHERE color = ? AND product_id = ?)";
        return query4Map(sql,param.get("userId"),param.get("color"),param.get("productId"));
    }

    @Override
    public int updateAmount(Map<String, Object> param) {
        String sql = "UPDATE cart SET amount = amount + ? WHERE user_id = ? AND property_id = (SELECT property_id FROM property WHERE color = ? AND product_id = ?)";
        return executeUpdate(sql,param.get("amount"),param.get("userId"), param.get("color"),param.get("productId"));
    }

    @Override
    public int doDelete(Map<String, Object> param) {
        String sql = "DELETE FROM cart WHERE user_id = ? AND property_id = (SELECT property_id FROM property WHERE color = ? AND product_id = ?)";
         return   executeUpdate(sql,param.get("userId"),param.get("color"),param.get("productId"));
    }
  /*  @Override
    public List<Map<String, Object>> queryByUserId(Integer userId) {
        String sql ="SELECT product.product_id,product.product_name,product.img_url,product.price,cart.amount,cart.color FROM product,cart  WHERE cart.product_id = product.product_id AND user_id = ? ";
        return query4MapList(sql,userId);
    }*/

    @Override
    public int addAmount(Map<String, Object> param) {
        String sql = "UPDATE cart SET amount = amount +1 WHERE user_id = ? AND property_id = (SELECT property_id FROM property WHERE color = ? AND product_id = ?)";
        return executeUpdate(sql, param.get("userId"), param.get("color"), param.get("productId"));
    }

    @Override
    public int subAmount(Map<String, Object> param) {
        String sql = "UPDATE cart SET amount = amount -1 WHERE user_id = ? AND property_id = (SELECT property_id FROM property WHERE color = ? AND product_id = ?)";
        return executeUpdate(sql,param.get("userId"),param.get("color"),param.get("productId"));
    }

    @Override
    public Map<String, Object> queryTotal(Integer userId, String[] cartIds) {
       StringBuilder sql = new StringBuilder("SELECT SUM(old_price*amount)-SUM(price*amount) total_discounts,SUM(amount) total_amount,SUM(amount*price) total_price FROM cart t1, property t2,product t3 WHERE t1.property_id = t2.property_id AND t2.product_id = t3.product_id  AND t1.user_id = ? AND cart_id IN(");
        Object[] param = new Object[cartIds.length + 1];
        param[0] = userId;
        for (int i =0;i<cartIds.length;i++) {
            sql.append("?,");
            param[i+1] = cartIds[i];
        }
        sql.deleteCharAt(sql.length()-1);
        sql.append(")");

        return  query4Map(sql.toString(),param);
    }

    @Override
    public List<Map<String, Object>> queryProduct4Checkout(Integer userId, String[] cartIds) {
        StringBuilder sql = new StringBuilder( "SELECT * FROM cart t1, property t2,product t3 WHERE t1.property_id = t2.property_id AND t2.product_id = t3.product_id  AND t1.user_id = ? AND cart_id IN(");
        Object[] param = new Object[cartIds.length + 1];
        param[0] = userId;
        for (int i =0;i<cartIds.length;i++) {
            sql.append("?,");
            param[i+1] = cartIds[i];
        }
        sql.deleteCharAt(sql.length()-1);
        sql.append(")");
        return  query4MapList(sql.toString(),param);
    }

    @Override
    public Map<String, Object> queryByUserIdANDProductId(Integer userId,Integer productId) {
        String sql = "SELECT * FROM cart t1,property t2,product t3 WHERE t1.property_id = t2.property_id AND t2.product_id = t3.product_id AND t1.user_id = ? AND t3.product_id = ?";
        return query4Map(sql,userId,productId);

    }

    @Override
    public int doDelete(Integer cartId) {
        String sql = "DELETE FROM cart WHERE cart_id = ?";
                  return   executeUpdate(sql,cartId);
    }

    @Override
    public Map<String, Object> queryIsCartOverAmount(Integer cartId, String color, Integer productId) {
        String sql = "SELECT * FROM cart,property WHERE cart.property_id = property.property_id AND cart_id = ? AND property.property_id =(SELECT property_id FROM property WHERE color = ? AND product_id = ?)  AND amount+1<=inventory";
        return  query4Map(sql,cartId,color,productId);

    }


}

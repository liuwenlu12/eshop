package com.oaec.eshop.dao.Impl;

import com.oaec.eshop.dao.OrderDao;
import com.oaec.eshop.util.CommonDao;

import java.util.List;
import java.util.Map;

public class OrderDaoImpl extends CommonDao implements OrderDao {
    @Override
    public List<Map<String, Object>> queryProductByOrderId(Integer orderId) {
       String sql = "SELECT order_detail.detail_id,order_detail.product_id,order_detail.img_url,order_detail.name,order_detail.price,order_detail.amount,order_detail.color,order_detail.status FROM orders,order_detail WHERE orders.order_id = order_detail.order_id AND orders.order_id = ? ORDER BY orders.add_time DESC";
        return query4MapList(sql,orderId);
    }

    @Override
    public List<Map<String, Object>> queryByUserId(Integer userId) {
        String sql = "SELECT * FROM orders WHERE user_id= ? ORDER BY add_time DESC";
        return  query4MapList(sql,userId);
    }

    @Override
    public Double getTotalPrice(Integer orderId) {
        String sql = "SELECT SUM(order_detail.amount*order_detail.price) total_price FROM orders,order_detail WHERE orders.order_id = order_detail.order_id AND orders.order_id = ?";
        Map<String, Object> map = query4Map(sql, orderId);
        return Double.parseDouble(map.get("total_price").toString());

    }

    @Override
    public int doInsert(Integer userId, Integer addressId) {
        String sql = "INSERT INTO orders VALUES (?,?,?,0,sysdate)";
        int orderId = getOrderId();
        int i =  executeUpdate(sql,orderId,userId,addressId);
        if(i==1){
            return  orderId;
        }
        return 0;
    }

    @Override
    public int doInsertDetail(Map<String, Object> param) {
        String sql = "INSERT INTO order_detail VALUES(seq_order_detail.nextval,?,?,?,?,?,?,?,0,null,?,null)";
        return executeUpdate(sql,param.get("order_id"),param.get("product_id"),param.get("product_name"),param.get("img_url"),param.get("price"),param.get("amount"),param.get("color"),param.get("user_id"));

    }

    @Override
    public int doChangeStatus(Integer userId, Integer orderId) {
        String sql = "UPDATE orders SET status = 1 WHERE user_Id = ? AND order_id = ? AND status = 0";
        return  executeUpdate(sql,userId,orderId);
    }

    @Override
    public List<Map<String, Object>> queryByUserIdANDOrderId(Integer userId, Integer CartId) {
       String sql = "SELECT * FROM orders,order_detail,address WHERE orders.order_id = order_detail.order_id AND address.address_id = orders.address_id AND ORDERS.user_id = ? AND orders.order_id = ? ORDER BY orders.add_time DESC";
       return  query4MapList(sql,userId,CartId);
    }

    @Override
    public Map<String, Object> queryDetailByDetailId(Integer detailId) {
        String sql = "SELECT * FROM order_detail WHERE detail_id = ?";
        return query4Map(sql,detailId);
    }

    @Override
    public int doUpdateDetailStatus(Integer detailId) {
        String sql = "UPDATE order_detail SET status = 1 WHERE detail_id = ?";
        return executeUpdate(sql,detailId);
    }

    private int getOrderId(){
        String sql = "SELECT seq_orders.nextval order_id FROM dual";
        Map<String, Object> map = query4Map(sql);
       return   Integer.parseInt(map.get("order_id").toString());

}


   /* @Override
    public int doInsertOrderDetail(Map<String, Object> param) {
        String sql = "INSERT INTO order_detail VALUES(seq_order_detail.nextval,?,?,?,?,?,?)";
        return   executeUpdate(param.get())
    }*/
}

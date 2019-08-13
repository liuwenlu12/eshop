package com.oaec.eshop.service.Impl;

import com.alibaba.fastjson.JSON;
import com.oaec.eshop.dao.AddressDao;
import com.oaec.eshop.dao.CartDao;
import com.oaec.eshop.dao.Impl.AddressDaoImpl;
import com.oaec.eshop.dao.Impl.CartDaoImpl;
import com.oaec.eshop.dao.Impl.OrderDaoImpl;
import com.oaec.eshop.dao.Impl.ProductDaoImpl;
import com.oaec.eshop.dao.OrderDao;
import com.oaec.eshop.dao.ProductDao;
import com.oaec.eshop.service.OrderService;

import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private AddressDao addressDao = new AddressDaoImpl();
private CartDao cartDao = new CartDaoImpl();
private ProductDao productDao = new ProductDaoImpl();
    @Override
    public List<Map<String, Object>> getOrders(Integer userId) {
        List<Map<String, Object>> orderList = orderDao.queryByUserId(userId);

        for (Map<String, Object> map : orderList) {
            int address_id = Integer.parseInt(map.get("address_id").toString());
            Map<String, Object> address = addressDao.queryByAddressId(address_id);
            map.put("address",address);
            int order_id = Integer.parseInt(map.get("order_id").toString());
            Double totalPrice = orderDao.getTotalPrice(order_id);
            map.put("totalPrice",totalPrice);
            List<Map<String, Object>> products = orderDao.queryProductByOrderId(order_id);

            map.put("products",products);
        }
        return orderList;

    }

    @Override
    public boolean submit(Integer userId,Integer addressId, String[] productIds) {
        int orderId = orderDao.doInsert(userId, addressId);
            int result = 0;
        for (String pid : productIds) {
            int productId = Integer.parseInt(pid);
            Map<String, Object> product = cartDao.queryByUserIdANDProductId(userId, productId);
            product.put("order_id",orderId);
            product.put("user_id",userId);
          result +=  orderDao.doInsertDetail(product);
            Object cart_id = product.get("cart_id");
            int cartId = Integer.parseInt(cart_id.toString());
            result +=  cartDao.doDelete(cartId);
            Object a = product.get("amount");
            int amount = Integer.parseInt(a.toString());
            String color = product.get("color").toString();
            result +=   productDao.updateInventoryAndSalesVolume(productId,amount,color);

        }
        return result > 0;

    }

    @Override
    public boolean changeOrderStatus(Integer userId, Integer orderId) {
       return orderDao.doChangeStatus(userId,orderId)==1;
    }

    @Override
    public List<Map<String, Object>> getOrderDetail(Integer userId, Integer orderId) {
        return  orderDao.queryByUserIdANDOrderId(userId,orderId);
    }

    @Override
    public Map<String, Object> getDetailByDetailId(Integer detailId) {
        return orderDao.queryDetailByDetailId(detailId);
    }
}

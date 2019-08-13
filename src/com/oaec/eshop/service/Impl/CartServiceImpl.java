package com.oaec.eshop.service.Impl;

import com.oaec.eshop.dao.CartDao;
import com.oaec.eshop.dao.Impl.CartDaoImpl;
import com.oaec.eshop.dao.Impl.PropertyDaoImpl;
import com.oaec.eshop.dao.PropertyDao;
import com.oaec.eshop.service.CartService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartServiceImpl implements CartService {
    private CartDao cartDao = new CartDaoImpl();
    private PropertyDao propertyDao = new PropertyDaoImpl();
    @Override
    public List<Map<String, Object>> getCartProducts(String userId) {
        return cartDao.queryByUserId(Integer.parseInt(userId));
    }

    @Override
    public boolean addCart(Integer amount, String color, Integer productId, Integer userId) {
       Map<String,Object> param = new HashMap<>();
        param.put("amount",amount);
        param.put("color",color);
        param.put("productId",productId);
        param.put("userId",userId);

        int result = 0;


            Map<String, Object> cart = cartDao.queryByUserIdANDProductIdANDColor(param);

            if (cart == null) {
                result = cartDao.doInsert(param);
            } else {
                result = cartDao.updateAmount(param);
            }


        return result == 1;
    }

    @Override
    public boolean deleteCart(Integer userId, String color, Integer productId) {
        Map<String,Object> param = new HashMap<>();
        param.put("color",color);
        param.put("productId",productId);
        param.put("userId",userId);
        return cartDao.doDelete(param)==1;
    }

    @Override
    public boolean updateAmount(String action, Integer userId, String color, Integer productId,Integer cartId) {
        int result = 0;
        Map<String,Object> param = new HashMap<>();
        param.put("color",color);
        param.put("productId",productId);
        param.put("userId",userId);


            if ("add".equals(action)) {
                if(cartDao.queryIsCartOverAmount(cartId,color,productId)!=null){
                    result = cartDao.addAmount(param);
                }

            } else {
                result = cartDao.subAmount(param);
            }

       return  result == 1;
    }

    @Override
    public Map<String, Object> getTotal(Integer userId, String[] cartIds) {
        return cartDao.queryTotal(userId,cartIds);
    }

    @Override
    public List<Map<String, Object>> getProducts4Checkout(Integer userId, String[] cartIds) {
        return cartDao.queryProduct4Checkout(userId,cartIds);
    }

    @Override
    public Boolean isOverAmount(Integer amount, String color, Integer productId,Integer userId) {
        Map map = new HashMap<>();

        map.put("userId",userId);
        map.put("productId",productId);
        map.put("color",color);
        int amount1 = 0;
       Map map1 = cartDao.queryByUserIdANDProductIdANDColor(map);
       if(map1==null){
           amount1 = 0;
       }else{
            amount1 = Integer.parseInt(map1.get("amount").toString());

       }
        Map<String, Object> property = propertyDao.queryByColorAndProductId(color, productId);
        int inventory = Integer.parseInt(property.get("inventory").toString());
        if((amount1+amount)<=inventory){
            return true;
        }else{
            return false;
        }

    }


}

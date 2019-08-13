package com.oaec.eshop.service.Impl;

import com.oaec.eshop.dao.AddressDao;
import com.oaec.eshop.dao.Impl.AddressDaoImpl;
import com.oaec.eshop.dao.Impl.OrderDaoImpl;
import com.oaec.eshop.dao.Impl.UserDaoImpl;
import com.oaec.eshop.dao.OrderDao;
import com.oaec.eshop.dao.UserDao;
import com.oaec.eshop.service.OrderService;
import com.oaec.eshop.service.PersonalService;

import java.util.List;
import java.util.Map;

public class PersonalServiceImpl implements PersonalService {
    private AddressDao addressDao = new AddressDaoImpl();
    private UserDao userDao = new UserDaoImpl();
    private OrderDao orderDao = new OrderDaoImpl();
    @Override
    public List<Map<String, Object>> getAddress(Integer userId) {

        return addressDao.queryByUserId(userId);
    }

    @Override
    public Map<String, Object> getUser(Integer userId) {

        return userDao.queryByUserId(userId);
    }

    @Override
    public Double getOrderDetailPrice(Integer orderId) {
        return
                orderDao.getTotalPrice(orderId);
    }
}

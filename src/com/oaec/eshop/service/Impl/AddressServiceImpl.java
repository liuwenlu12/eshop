package com.oaec.eshop.service.Impl;

import com.oaec.eshop.dao.AddressDao;
import com.oaec.eshop.dao.Impl.AddressDaoImpl;
import com.oaec.eshop.service.AddressService;

import java.util.List;
import java.util.Map;

public class AddressServiceImpl implements AddressService {
    private AddressDao addressDao = new AddressDaoImpl();

    @Override
    public List<Map<String, Object>> getAddress(Integer userId) {
        return addressDao.queryByUserId(userId);
    }

    @Override
    public Boolean doDelete(String addressId, String userId) {
        int aId = Integer.parseInt(addressId);
        int uId = Integer.parseInt(userId);
        return addressDao.deleteByAddressIdAndUserId(aId,uId)==1;
    }

    @Override
    public Boolean doUpdate(String userId,String address ,String telNumber,String name,String addressId) {
        int uId = Integer.parseInt(userId);
        int aId = Integer.parseInt(addressId);

        return addressDao.updateAddressByUserIdAndAddressId(uId,address,telNumber,name,aId)==1;
    }

    @Override
    public Boolean doInsert(String name, String telNumber, String address, String userId) {
        int uId = Integer.parseInt(userId);
        return  addressDao.doInsert(name,telNumber,address,uId)==1;
    }
}

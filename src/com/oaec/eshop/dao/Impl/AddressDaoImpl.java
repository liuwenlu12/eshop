package com.oaec.eshop.dao.Impl;

import com.oaec.eshop.dao.AddressDao;
import com.oaec.eshop.util.CommonDao;

import java.util.List;
import java.util.Map;

public class AddressDaoImpl extends CommonDao implements AddressDao {
    @Override
    public Map<String, Object> queryByAddressId(Integer addressId) {
        String sql = "SELECT * FROM address WHERE address_id = ? ";
        return query4Map(sql,addressId);
    }

    @Override
    public List<Map<String, Object>> queryByUserId(Integer userId) {
        String sql = "SELECT * FROM address WHERE  status = 1 AND user_id = ?";
        return query4MapList(sql,userId);
    }

    @Override
    public int deleteByAddressIdAndUserId(Integer addressId,Integer userId) {
        String sql = "UPDATE  address SET status = 0 WHERE address_id = ? AND user_id = ?";
        return  executeUpdate(sql,addressId,userId);

    }

    @Override
    public int updateAddressByUserIdAndAddressId(Integer userId,String address ,String telNumber,String name,Integer addressId) {
        String sql = "UPDATE address SET detailed_address = ?,address_name = ?,tel_number = ?  WHERE user_id = ? AND address_id = ? ";
        return  executeUpdate(sql,address,name,telNumber,userId,addressId);
    }

    @Override
    public int doInsert(String name, String telNumber, String address, Integer userId) {
        String sql = null;
        String sql1 = "SELECT * FROM address WHERE user_id = ? AND status = 1";
        Map<String, Object> addressMap = query4Map(sql1, userId);
        if(addressMap!=null){
             sql = "INSERT INTO address VALUES(seq_address.nextval,?,?,?,?,0,1,sysdate)";
        }else{
            sql = "INSERT INTO address VALUES(seq_address.nextval,?,?,?,?,1,1,sysdate)";
        }

        return executeUpdate(sql,name,telNumber,address,userId);
    }


}

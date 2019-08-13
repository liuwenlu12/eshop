package com.oaec.eshop.service.Impl;

import com.oaec.eshop.dao.BrandDao;
import com.oaec.eshop.dao.Impl.BrandDaoImpl;
import com.oaec.eshop.service.BrandService;

import java.util.List;
import java.util.Map;

public class BrandServiceImpl implements BrandService {
    private BrandDao brandDao = new BrandDaoImpl();

    @Override
    public List<Map<String, Object>> query(String typeId) {
        if(typeId!=null){
            return brandDao.queryByTypeId(Integer.parseInt(typeId));
        }else{
            return brandDao.queryAll();
        }

    }
}

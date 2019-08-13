package com.oaec.eshop.service.Impl;

import com.alibaba.fastjson.JSON;
import com.oaec.eshop.dao.*;
import com.oaec.eshop.dao.Impl.*;
import com.oaec.eshop.service.ProductService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductServiceImpl implements ProductService {
    private ProductDao productDao = new ProductDaoImpl();
    private BrandDao brandDao = new BrandDaoImpl();
    private TypeDao typeDao = new TypeDaoImpl();
    private PropertyDao propertyDao = new PropertyDaoImpl();
    private OrderDao orderDao = new OrderDaoImpl();
    private UserDao userDao = new UserDaoImpl();
private PictureDao pictureDao = new PictureDaoImpl();
    @Override
    public List<Map<String, Object>> query(String name,String typeId,String brandId,String page) {

        if(brandId!=null && typeId !=null){

            return   productDao.queryByBrandIdAndTypeId(Integer.parseInt(brandId),Integer.parseInt(typeId));
        }

        if(typeId!=null){

            return productDao.queryByTypeId(Integer.parseInt(typeId));
        }
        if(brandId!=null){

            return productDao.queryByBrandId(Integer.parseInt(brandId));
        }

        if(name==null && brandId==null && typeId ==null){
            int page1 = Integer.parseInt(page);

            return productDao.queryAll(page1);
        }


        if(name!=null) {

            return productDao.queryLike(name);
        }
        return null;
    }

    @Override
    public int getCount(String name, String typeId, String brandId) {
        if(brandId!=null && typeId !=null){

            return  0;
        }

        if(typeId!=null){

            return 0;
        }
        if(brandId!=null){

            return 0;
        }

        if(name==null && brandId==null && typeId ==null){


            return productDao.getCountAll();
        }


        if(name!=null) {

            return 0;
        }
        return 0;
    }

    @Override
    public List<Map<String, Object>> queryHot() {
        return productDao.queryBySales();
    }


    @Override
    public Map<String, Object> getDetail(String productId) {
        Map<String, Object> detail = productDao.queryByProductId(Integer.parseInt(productId));
        Integer brand_id = Integer.parseInt(detail.get("brand_id").toString());
        Integer type_id = Integer.parseInt(detail.get("type_id").toString());
        Integer product_id = Integer.parseInt(detail.get("product_id").toString());
        List<Map<String, Object>> property = propertyDao.queryByProductId(product_id);
        List<Map<String, Object>> picture = pictureDao.queryByProductId(product_id);

        Map<String, Object> brand = brandDao.queryByBrandId(brand_id);
        Map<String, Object> type = typeDao.queryByTypeId(type_id);

        detail.put("brand",brand);
        detail.put("type",type);
        detail.put("property",property);
        detail.put("picture",picture);
        return  detail;


    }

    @Override
    public List<Map<String, Object>> queryNew() {
        return productDao.queryByAddTime();
    }

    @Override
    public List<Map<String, Object>> queryLowPrice() {
        return productDao.queryByPriceAndOldPrice();
    }

    @Override
    public Boolean Appraise(Integer userId, String content, Integer productId,Integer detailId) {
        if(productDao.doInsertAppraise(userId,content,productId,detailId)==1){
            if(orderDao.doUpdateDetailStatus(detailId)==1){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Map<String,Object>> getAppraise(String productId) {
        List<Map<String, Object>> userIdList = userDao.queryAllUserId();
        List<Map<String,Object>> appraiseList = new ArrayList<>();
        int pid = Integer.parseInt(productId);
        for (Map<String, Object> userIdMap : userIdList) {
            int userId1 = Integer.parseInt(userIdMap.get("user_id").toString());
            List<Map<String, Object>> maps = productDao.queryAppraiseByProductIdAndUserId(pid, userId1);
            for (Map<String, Object> map : maps) {
                appraiseList.add(map);
            }
        }
        return appraiseList;
    }

    @Override
    public List<Map<String, Object>> getLowPrice() {
        return productDao.queryByPrice();
    }
}

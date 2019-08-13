package com.oaec.eshop.service.Impl;



import com.oaec.eshop.dao.Impl.UserDaoImpl;
import com.oaec.eshop.dao.UserDao;
import com.oaec.eshop.entity.User;
import com.oaec.eshop.service.UserService;

import java.util.HashMap;
import java.util.Map;


public class UserServiceImpl implements UserService {
	private UserDao userDao =  new UserDaoImpl();


	@Override
	public Map<String, Object> login(String username, String password) {

        Map<String, Object> map = userDao.queryByUsername(username);
        if(map==null){
            map = new HashMap<>();
			map.put("error", "用户名不存在");
		}else{
			if(map.get("password").equals(password)){
			    map.put("user",map);
				return map;
			}else{
			    map.clear();
				map.put("error", "密码错误");
			}
		}
		return map;

	}

	@Override
	public Map<String, Object> register(String username, String password,String telNumber) {
		//Map<String, Object> map = new HashMap<>();
        Map<String, Object> map = userDao.queryByUsername(username);
        Map<String, Object> telMap = userDao.queryByTelNumber(telNumber);
        if(map!=null){
			map.put("error", "用户名已存在");


        }else if(telMap!=null){

            map = new HashMap<>();
            map.put("error", "电话号码已存在");

		}else{
            map = new HashMap<>();
            boolean doInsert = userDao.doInsert(username, password,telNumber);
            map.put("success", doInsert);
        }
		return map;
	}

	@Override
	public Map<String, Object> updatePassWord(Integer userId,String password,String telNumber,String newPassword) {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> passMap = userDao.queryPassWordByUserId(userId);
		Map<String, Object> telMap = userDao.queryTelNumberByUserId(userId);
		if(!passMap.get("password").equals(password)){
			map.put("error","输入原密码不正确");
			return map;
		}else if(!telMap.get("tel_number").equals(telNumber)){
				map.put("error","输入号码不正确");
				return map;
		}else{

			int i = userDao.updatePassWord(newPassword, userId);
			if(i==1){
				map.put("success", "成功");
			}else{
				map.put("error","修改失败");
			}
		}

		return map;
	}

	@Override
	public Boolean getUserName(String username) {

		if(userDao.queryUserName(username)!=null){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public Boolean getTelNumber(String username, String telNumber) {
		Map<String, Object> map = userDao.queryTelNumberByUserNameAndTelNumber(username, telNumber);
		if(map!=null){
			String tel_number = map.get("tel_number").toString();
			if(tel_number.equals(telNumber)){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}

	}

	@Override
	public Boolean forgetPassWord(String username, String password) {
		return userDao.forgetUpdatePassWord(username,password)==1;
	}

}

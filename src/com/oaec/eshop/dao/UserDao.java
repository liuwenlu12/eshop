package com.oaec.eshop.dao;


import com.oaec.eshop.entity.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
	/**
	 * 用户登录
	 * @param username 用户名
	 * @param password 密码
	 * @return
	 */
	Map<String,Object> queryByUsernameAndPassword(String username, String password);

	/**
	 * 用户注册
	 * @param username 用户名
	 * @param password 密码
	 * @return
	 */
	boolean doInsert(String username, String password,String telNumber);

	Map<String,Object> queryByUsername(String username);


	Map<String,Object> queryByTelNumber(String telNumber);

	Map<String,Object> queryByUserId(Integer userId);

	Map<String,Object> queryTelNumberByUserId(Integer userId);

	Map<String,Object> queryPassWordByUserId(Integer userId);

	int updatePassWord(String password,Integer userId);

	List<Map<String ,Object>> queryAllUserId();

	Map<String,Object>  queryUserName(String username);

	Map<String,Object>  queryTelNumberByUserNameAndTelNumber(String username,String telNumber);

	int forgetUpdatePassWord(String username,String password);
 }
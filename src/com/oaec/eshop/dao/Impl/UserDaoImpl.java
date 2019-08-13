package com.oaec.eshop.dao.Impl;


import com.oaec.eshop.dao.UserDao;
import com.oaec.eshop.entity.User;
import com.oaec.eshop.util.CommonDao;

import java.util.List;
import java.util.Map;

public class UserDaoImpl extends CommonDao implements UserDao {

	@Override
	public Map<String,Object> queryByUsernameAndPassword(String username, String password) {
		String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
		return query4Map(sql,username,password);
	}

	@Override
	public boolean doInsert(String username, String password,String telNumber) {
		String sql = "INSERT INTO users VALUES(seq_users.nextval,?,?,1,?)";
		int i = executeUpdate(sql, username, password,telNumber);
		return i == 1;
	}

	@Override
	public Map<String,Object> queryByUsername(String username) {
		String sql = "SELECT * FROM users WHERE username = ?";
		return query4Map(sql,username);
	}

	@Override
	public Map<String, Object> queryByTelNumber(String telNumber) {
		String sql = "SELECT * FROM users WHERE tel_number= ?";
		return  query4Map(sql,telNumber);
	}

	@Override
	public Map<String, Object> queryByUserId(Integer userId) {
		String sql = "SELECT * FROM users WHERE user_id = ?";
		return query4Map(sql,userId);
	}

	@Override
	public Map<String, Object> queryTelNumberByUserId(Integer userId) {
		String sql = "SELECT tel_number FROM users WHERE user_id = ?";
		return query4Map(sql,userId);
	}

	@Override
	public Map<String, Object> queryPassWordByUserId(Integer userId) {
		String sql = "SELECT password FROM users WHERE user_id = ?";
		return  query4Map(sql,userId);
	}

	@Override
	public int updatePassWord(String password,Integer userId) {
		String sql = "UPDATE users SET password = ? WHERE user_id = ?";
		return executeUpdate(sql,password,userId);
	}

	@Override
	public List<Map<String, Object>> queryAllUserId() {
		String sql = "SELECT * FROM users";
		return query4MapList(sql);
	}

	@Override
	public Map<String, Object> queryUserName(String username) {
		String sql = "SELECT * FROM users WHERE username = ?";
		return query4Map(sql,username);
	}

	@Override
	public Map<String, Object> queryTelNumberByUserNameAndTelNumber(String username, String telNumber) {
		String sql = "SELECT * FROM users WHERE username = ? AND tel_number = ?";
		return  query4Map(sql,username,telNumber);
	}

	@Override
	public int forgetUpdatePassWord(String username, String password) {
		String sql = "UPDATE users SET password = ? WHERE username = ?";
		return executeUpdate(sql,password,username);
	}


}

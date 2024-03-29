package com.tcejorptset.servl.dao;

import java.util.Map;

import com.tcejorptset.servl.bean.User;
import com.tcejorptset.servl.bean.UserInfo;

public interface IUserDAO {
	
	Integer getUserId (String login, String password) throws DaoException;
	
	Integer getUserIdByToken (String selector, String validator) throws DaoException;

	UserInfo getUserInfo(int userId) throws DaoException;

	String getRole(int userId) throws DaoException;
	
	boolean registration(User user, UserInfo userInfo) throws DaoException;
	
	Map <String, String> addUpdateToken (int userId) throws DaoException;
	
	
}

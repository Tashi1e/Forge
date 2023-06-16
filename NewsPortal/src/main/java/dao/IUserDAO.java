package dao;

import bean.UserInfo;

public interface IUserDAO {
	
	boolean logination(String login, String password) throws DaoException;
	boolean registration(UserInfo user) throws DaoException;
	String getRole(String login, String password) throws DaoException;
	String getNickName(String login) throws DaoException;
}

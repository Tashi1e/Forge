package dao;

import bean.User;
import bean.UserInfo;

public interface IUserDAO {
	
	String getRole(String login, String password) throws DaoException;
	UserInfo getUserInfo(String login) throws DaoException;
	boolean registration(User user, UserInfo userInfo) throws DaoException;
}

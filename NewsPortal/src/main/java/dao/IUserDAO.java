package dao;

import bean.User;
import bean.UserInfo;

public interface IUserDAO {

	UserInfo getUserInfo(String login) throws DaoException;

	String getRole(String login, String password) throws DaoException;

	boolean registration(User user, UserInfo userInfo) throws DaoException;
}

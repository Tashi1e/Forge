package dao.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

import dao.DaoException;
import bean.UserInfo;
import dao.DaoException;
import dao.IUserDAO;
import util.tempUserSource.TempUserSource;

@SuppressWarnings("unused")
public class UserDAO_temp implements IUserDAO {

	TempUserSource tempUserSource = new TempUserSource();
	UserInfo user = null;

	public boolean logination(String login, String password) throws DaoException {

		try {
			user = tempUserSource.checkLogPass(login, password);
			if (user!=(null))
				return true;
			else
				return false;
		} catch (ClassNotFoundException e) {
			throw new DaoException(e);
		} catch (IOException e) {
			throw new DaoException(e);
		}

	}

	public String getRole(String login, String password) throws DaoException {

		if (!user.equals(null))
			return user.getRole();
		else
			return "guest";
	}
	
	public String getNickName(String login) throws DaoException {

		if (!user.equals(null))
			return user.getNickName();
		else
			return null;
	}

	public boolean registration(UserInfo user) throws DaoException {
		try {
				return tempUserSource.saveNewUser(user);

		} catch (ClassNotFoundException e) {
			throw new DaoException(e);
		} catch (IOException e) {
			throw new DaoException(e);
		}
	}

}

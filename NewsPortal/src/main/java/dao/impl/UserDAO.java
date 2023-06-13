package dao.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

import dao.DaoException;
import bean.UserInfo;
import bean.tempUserSource.TempUserSource;
import dao.DaoException;
import dao.IUserDAO;

@SuppressWarnings("unused")
public class UserDAO implements IUserDAO {

	TempUserSource tempUserSource = new TempUserSource();

	public boolean logination(String login, String password) throws DaoException {

		try {
			if (tempUserSource.checkLogPass(login, password)!=(null))
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

		try {
			UserInfo user = tempUserSource.checkLogPass(login, password);
			if (!user.equals(null))
				return user.getRole();
			else
				return "guest";

		} catch (ClassNotFoundException e) {
			throw new DaoException(e);
		} catch (IOException e) {
			throw new DaoException(e);
		}
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

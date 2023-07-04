package service.impl;

import bean.UserInfo;
import bean.User;
import dao.DaoException;
import dao.DaoProvider;
import dao.IUserDAO;
import service.ServiceException;
import service.IUserService;

public class UserServiceImpl implements IUserService {

	private final IUserDAO userDAO = DaoProvider.getInstance().getUserDao();

	@Override
	public String signIn(String login, String password) throws ServiceException {

		try {
			String role = userDAO.getRole(login, password);
			if (role != null) {
				return role;
			} else {
				return "guest";
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	public String userNickName(String login) throws ServiceException {

		try {
			if (userDAO.getUserInfo(login) != null) {
				return userDAO.getUserInfo(login).getNickName();
			} else {
				return null;
			}

		} catch (DaoException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public boolean registration(User user, UserInfo userInfo) throws ServiceException {
		try {
			return userDAO.registration(user, userInfo);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public UserInfo userInfo(String login) throws ServiceException {
		try {
			return userDAO.getUserInfo(login);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
}

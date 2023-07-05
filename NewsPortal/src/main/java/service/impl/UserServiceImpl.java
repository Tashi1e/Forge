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
	public String signIn(String login_selector, String password_validator) throws ServiceException {

		String role;
		try {
			role = userDAO.getRole(getUserId(login_selector, password_validator));
			if (role != null) {
				return role;
			} else {
				return "guest";
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	public String userNickName(String login_selector, String password_validator) throws ServiceException {

		UserInfo userInfo = userInfo(login_selector, password_validator);
		if (userInfo != null) {
			return userInfo.getNickName();
		} else {
			return null;
		}
	}

	@Override
	public UserInfo userInfo(String login_selector, String password_validator) throws ServiceException {

		UserInfo userInfo;
		try {
			userInfo = userDAO.getUserInfo(getUserId(login_selector, password_validator));
			return userInfo;
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
	public boolean addUserToken(String login, String password) throws ServiceException {
		try {
			Integer userId = userDAO.getUserId(login, password);
			if (userId != null) {
				return userDAO.addToken(userId, login, password);
			} else {
				return false;
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	private Integer getUserId(String login_selector, String password_validator) throws ServiceException {
		Integer userId;
		try {
			userId = userDAO.getUserIdByToken(login_selector, password_validator);
			if (userId == null) {
				userId = userDAO.getUserId(login_selector, password_validator);
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return userId;
	}
}

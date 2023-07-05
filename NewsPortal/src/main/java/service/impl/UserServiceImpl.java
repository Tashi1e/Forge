package service.impl;

import bean.UserInfo;

import java.util.Map;

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

		String role = null;
		try {
			Integer userId = getUserId(login_selector, password_validator);
			System.out.println("userService, signIn, userId = "+ userId); // GARBAGE
			if (userId != null) {
				role = userDAO.getRole(userId);
				if (role == null) {
					role = "guest";
				}
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return role;
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

		try {
			Integer userId = getUserId(login_selector, password_validator);
			if (userId != null) {
				UserInfo userInfo = userDAO.getUserInfo(userId);
				return userInfo;
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
	public Map<String, String> addUserToken(String login, String password) throws ServiceException {
		try {
			Integer userId = userDAO.getUserId(login, password);
			if (userId != null) {
				return userDAO.addToken(userId, login, password);
			} else {
				return null;
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	private Integer getUserId(String login_selector, String password_validator) throws ServiceException {
		Integer userId;
		try {
			System.out.println("userService -> getUserId -> login_selector = " + login_selector); // GARBAGE
			userId = userDAO.getUserIdByToken(login_selector, password_validator);
			System.out.println("userService -> getUserId -> byToken -> userId = "+ userId); // GARBAGE
			if (userId == null) {
				userId = userDAO.getUserId(login_selector, password_validator);
				System.out.println("userService -> getUserId -> byLogPass -> userId = "+ userId); // GARBAGE
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return userId;
	}
}

package tcejorptset.servl.service.impl;

import java.util.Map;

import tcejorptset.servl.bean.User;
import tcejorptset.servl.bean.UserInfo;
import tcejorptset.servl.dao.DaoException;
import tcejorptset.servl.dao.DaoProvider;
import tcejorptset.servl.dao.IUserDAO;
import tcejorptset.servl.service.IUserService;
import tcejorptset.servl.service.ServiceException;

public class UserServiceImpl implements IUserService {

	private final IUserDAO userDAO = DaoProvider.getInstance().getUserDao();

	@Override
	public String signIn(String loginSelector, String passwordValidator) throws ServiceException {
//FIXME
		String role = null;
		try {
			Integer userId = getUserId(loginSelector, passwordValidator);
//			System.out.println("userService, signIn, userId = "+ userId); // GARBAGE
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

	@Override
	public UserInfo getUserInfo(String loginSelector, String passwordValidator) throws ServiceException {

		try {
			Integer userId = getUserId(loginSelector, passwordValidator);
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

	private Integer getUserId(String loginSelector, String passwordValidator) throws ServiceException {
		Integer userId;
		try {
//			System.out.println("userService -> getUserId -> login_selector = " + loginSelector); // TEST
			userId = userDAO.getUserIdByToken(loginSelector, passwordValidator);
			System.out.println("userService -> getUserId -> byToken -> userId = "+ userId); // TEST
			if (userId == null) {
				userId = userDAO.getUserId(loginSelector, passwordValidator);
				System.out.println("userService -> getUserId -> byLogPass -> userId = "+ userId); // TEST
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return userId;
	}
}

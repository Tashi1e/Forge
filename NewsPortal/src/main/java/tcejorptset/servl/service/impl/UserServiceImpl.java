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
	
	private static final String NO_ROLE_FOUNDED = "guest";
	private final IUserDAO userDAO = DaoProvider.getInstance().getUserDao();

	@Override
	public String signIn(String login, String password) throws ServiceException {
		try {
			Integer userId = userDAO.getUserId(login, password);
			return getRole(userId);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
	
	
	@Override
	public String signInByToken(String selector, String validator) throws ServiceException {
		try {
			Integer userId = userDAO.getUserIdByToken(selector, validator);
			return getRole(userId);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public UserInfo getUserInfo(String login, String password) throws ServiceException {
			Integer userId;
			try {
				userId = userDAO.getUserId(login, password);
			} catch (DaoException e) {
				throw new ServiceException(e);
			}
			return getUserInfo(userId);
	}
	
	@Override
	public UserInfo getUserInfoByToken(String selector, String validator) throws ServiceException {
		Integer userId;
		try {
			userId = userDAO.getUserIdByToken(selector, validator);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return getUserInfo(userId);
	}

	@Override
	public UserInfo getUserInfo (Integer userId) throws ServiceException {

		try {
			if (userId != null) {
			return userDAO.getUserInfo(userId);
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
	public Map <String, String> addUserToken(String login, String password) throws ServiceException {
		try {
			Integer userId = userDAO.getUserId(login, password);
			return tokenOps(userId);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public Map <String, String> updateUserToken(String selector, String validator) throws ServiceException {
		try {
			Integer userId = userDAO.getUserIdByToken(selector, validator);
			System.out.println("userId = " + userId); // TEST
			return tokenOps(userId);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
	
	private Map <String, String> tokenOps (Integer userId) throws ServiceException{
		try {
		if (userId != null) {
			return userDAO.addUpdateToken(userId);
		} else {
			return null;
		}
	} catch (DaoException e) {
		throw new ServiceException(e);
	}
	}

	private String getRole(Integer userId) throws ServiceException {
		String role;
		try {
		if (userId == null) {
			return NO_ROLE_FOUNDED;
		}
		role = userDAO.getRole(userId);
		if (role == null) {
			return NO_ROLE_FOUNDED;
		}
		return role;
		} catch (DaoException e) {
			throw new ServiceException(e);
		} 
	}
	
}

package service.impl;

import bean.UserInfo;
import dao.DaoException;
import dao.DaoProvider;
import dao.IUserDAO;
import service.ServiceException;
import service.IUserService;

public class UserServiceImpl implements IUserService {

	private final IUserDAO userDAO = DaoProvider.getInstance().getUserDao();
//	private final UserDataValidation userDataValidation = ValidationProvider.getIntsance().getUserDataVelidation();

	@Override
	public String signIn(String login, String password) throws ServiceException {

		/*
		 * if(!userDataValidation.checkAUthData(login, password)) { throw new
		 * ServiceException("login ...... "); }
		 */

		try {
			if (userDAO.logination(login, password)) {
				return userDAO.getRole(login, password);
			} else {
				return "guest";
			}

		} catch (DaoException e) {
			throw new ServiceException(e);
		}

	}
	
	public String userNickName(String login, String password) throws ServiceException {

				try {
			if (userDAO.logination(login, password)) {
				return userDAO.getNickName(login);
			} else {
				return null;
			}

		} catch (DaoException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public boolean registration(UserInfo user) throws ServiceException {
		try {
			if (userDAO.registration(user))
				return true;
			else
				return false;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
}

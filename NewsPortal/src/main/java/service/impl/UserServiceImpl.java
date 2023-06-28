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
// TODO
//	private final UserDataValidation userDataValidation = ValidationProvider.getIntsance().getUserDataVelidation();

	@Override
	public String signIn(String login, String password) throws ServiceException {
// TODO
		/*
		 * if(!userDataValidation.checkAUthData(login, password)) { throw new
		 * ServiceException("login ...... "); }
		 */

		try {
			String userRole = userDAO.getRole(login, password);
			if (userRole != null) {
				return userRole;
			} else {
				return "guest";
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}

	}

	public String userNickName(String login, String password) throws ServiceException {

		try {
			if (userDAO.getRole(login, password)!=null) {
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
			if (userDAO.registration(user, userInfo))
				return true;
			else
				return false;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
}

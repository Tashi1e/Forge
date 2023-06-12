package service.impl;

import bean.UserInfo;
import dao.DaoException;
import dao.DaoProvider;
import dao.IUserDAO;
import service.ServiceException;
import service.IUserService;

public class UserServiceImpl implements IUserService{

	private final IUserDAO userDAO = DaoProvider.getInstance().getUserDao();
//	private final UserDataValidation userDataValidation = ValidationProvider.getIntsance().getUserDataVelidation();
	
	@Override
	public String signIn(String login, String password) throws ServiceException {
		
		/*
		 * if(!userDataValidation.checkAUthData(login, password)) { throw new
		 * ServiceException("login ...... "); }
		 */
		
		try {
			if(userDAO.logination(login, password)) {
				return userDAO.getRole(login, password);
			}else {
				return "guest";
			}
			
		}catch(DaoException e) {
			throw new ServiceException(e);
		}
		
	}

	@Override
	public boolean registration(UserInfo user) {
		// TODO Auto-generated method stub
		return false;
	}

}

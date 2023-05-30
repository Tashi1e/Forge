package service;

import bean.NewUserInfo;

public interface IUserService {
	
	String signIn(String login, String password) throws ServiceException;
	boolean registration(NewUserInfo user) throws ServiceException;

}

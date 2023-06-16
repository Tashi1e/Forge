package service;

import bean.UserInfo;

public interface IUserService {
	
	String signIn(String login, String password) throws ServiceException;
	String userNickName(String login, String password) throws ServiceException;
	boolean registration(UserInfo user) throws ServiceException;

}

package service;

import bean.User;
import bean.UserInfo;

public interface IUserService {
	
	String signIn(String login, String password) throws ServiceException;
	String userNickName(String login) throws ServiceException;
	UserInfo userInfo (String login) throws ServiceException;
	boolean registration(User user, UserInfo userInfo) throws ServiceException;

}

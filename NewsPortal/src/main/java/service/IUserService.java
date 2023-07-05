package service;

import bean.User;
import bean.UserInfo;

public interface IUserService {
	
	String signIn(String login_selector, String password_validator) throws ServiceException;
	String userNickName(String login_selector, String password_validator) throws ServiceException;
	UserInfo userInfo (String login_selector, String password_validator) throws ServiceException;
	boolean registration(User user, UserInfo userInfo) throws ServiceException;
	boolean addUserToken(String login, String password) throws ServiceException;

}

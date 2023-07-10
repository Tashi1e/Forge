package service;

import java.util.Map;

import bean.User;
import bean.UserInfo;

public interface IUserService {
	
	String signIn(String loginSelector, String passwordValidator) throws ServiceException;
	UserInfo getUserInfo (String loginSelector, String passwordValidator) throws ServiceException;
	boolean registration(User user, UserInfo userInfo) throws ServiceException;
	Map <String, String> addUserToken(String selector, String validator) throws ServiceException;

}

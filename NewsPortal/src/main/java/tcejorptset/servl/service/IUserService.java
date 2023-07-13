package tcejorptset.servl.service;

import java.util.Map;

import tcejorptset.servl.bean.User;
import tcejorptset.servl.bean.UserInfo;

public interface IUserService {
	
	String signIn(String loginSelector, String passwordValidator) throws ServiceException;
	UserInfo getUserInfo (String loginSelector, String passwordValidator) throws ServiceException;
	boolean registration(User user, UserInfo userInfo) throws ServiceException;
	Map <String, String> addUserToken(String selector, String validator) throws ServiceException;

}

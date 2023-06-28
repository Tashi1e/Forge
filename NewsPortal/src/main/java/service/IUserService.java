package service;

import bean.UserInfo;
import bean.User;

public interface IUserService {
	
	String signIn(String login, String password) throws ServiceException;
	String userNickName(String login, String password) throws ServiceException;
	boolean registration(User user, UserInfo userInfo) throws ServiceException;

}

package dao;

import dao.impl.NewsDAO;
import dao.impl.UserDAO;

public final class DaoProvider {
	private static final DaoProvider instance = new DaoProvider();

	private final IUserDAO userDao = new UserDAO();
	private final INewsDAO newsDAO = new NewsDAO();
	
	
	private DaoProvider() {
	}
	
	
	public IUserDAO getUserDao() {
		return userDao;
	}
	
	public INewsDAO getNewsDAO() {
		return newsDAO;
	}

	public static DaoProvider getInstance() {
		return instance;
	}
}

package tcejorptset.servl.service;

import tcejorptset.servl.service.impl.ConnectionServiceImpl;
import tcejorptset.servl.service.impl.NewsServiceImpl;
import tcejorptset.servl.service.impl.UserServiceImpl;

public final class ServiceProvider {
	private static final ServiceProvider instance = new ServiceProvider();

	private ServiceProvider() {}

	private final IUserService userService = new UserServiceImpl();
	private final INewsService newsService = new NewsServiceImpl();
	private final ConnectionService connectionService = new ConnectionServiceImpl();

	public INewsService getNewsService() {
		return newsService;
	}

	public IUserService getUserService() {
		return userService;
	}

	public ConnectionService getConnectionService() {
		return connectionService;
	}

	public static ServiceProvider getInstance() {
		return instance;
	}

}

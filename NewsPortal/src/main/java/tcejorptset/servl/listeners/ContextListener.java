package tcejorptset.servl.listeners;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import tcejorptset.servl.controller.impl.GoToErrorPage;
import tcejorptset.servl.dao.impl.pool.ConnectionPool;
import tcejorptset.servl.dao.impl.pool.ConnectionPoolException;

public class ContextListener implements ServletContextListener{
	
	private final ConnectionPool connectionPool = ConnectionPool.getInstance();

	@Override
	public void contextInitialized(ServletContextEvent event) {
		try {
			connectionPool.initPoolData();
			System.out.println("Connection Pool Initialize Successful!");
		}
		catch(ConnectionPoolException e){
			new GoToErrorPage ("ConnectionPullInitError");
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		connectionPool.dispose();
	}

}

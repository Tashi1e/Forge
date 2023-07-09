package listeners;

import controller.impl.GoToErrorPage;
import dao.impl.pool.ConnectionPool;
import dao.impl.pool.ConnectionPoolException;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

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

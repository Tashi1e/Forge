package tcejorptset.servl.controller.listeners;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletException;
import tcejorptset.servl.controller.Command;
import tcejorptset.servl.controller.CommandProvider;
import tcejorptset.servl.controller.impl.GoToErrorPage;
import tcejorptset.servl.dao.impl.pool.ConnectionPool;
import tcejorptset.servl.dao.impl.pool.ConnectionPoolException;

public class ConnectionPoolOpsListener implements ServletContextListener{
	
	private final ConnectionPool connectionPool = ConnectionPool.getInstance();

	@Override
	public void contextInitialized(ServletContextEvent event) {
		try {
			connectionPool.initPoolData();
			System.out.println("Connection Pool Initialize Successful!"); //TEST
		}
		catch(ConnectionPoolException e){
			// TODO logination
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		connectionPool.dispose();
	}

}

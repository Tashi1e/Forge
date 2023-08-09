package com.tcejorptset.servl.controller.listeners;

import com.tcejorptset.servl.dao.impl.pool.ConnectionPool;
import com.tcejorptset.servl.dao.impl.pool.ConnectionPoolException;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class ConnectionPoolOpsListener implements ServletContextListener{
	
	private final ConnectionPool connectionPool = ConnectionPool.getInstance();

	@Override
	public void contextInitialized(ServletContextEvent event) {
		try {
			connectionPool.initPoolData();
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

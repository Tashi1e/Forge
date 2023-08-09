package com.tcejorptset.servl.service.impl;

import com.tcejorptset.servl.dao.impl.pool.ConnectionPool;
import com.tcejorptset.servl.dao.impl.pool.ConnectionPoolException;
import com.tcejorptset.servl.service.ConnectionService;
import com.tcejorptset.servl.service.ServiceException;

@Deprecated
public class ConnectionServiceImpl implements ConnectionService {
	
	ConnectionPool connectionPool = ConnectionPool.getInstance();

	@Override
	public void initPool() throws ServiceException {
		try {
			connectionPool.initPoolData();
		} catch (ConnectionPoolException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void closePool() {
			connectionPool.dispose();
	}

}

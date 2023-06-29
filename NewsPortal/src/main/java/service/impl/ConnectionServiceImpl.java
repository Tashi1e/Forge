package service.impl;

import dao.impl.pool.ConnectionPool;
import dao.impl.pool.ConnectionPoolException;
import service.ConnectionService;
import service.ServiceException;

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

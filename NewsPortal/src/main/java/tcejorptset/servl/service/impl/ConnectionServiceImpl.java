package tcejorptset.servl.service.impl;

import tcejorptset.servl.dao.impl.pool.ConnectionPool;
import tcejorptset.servl.dao.impl.pool.ConnectionPoolException;
import tcejorptset.servl.service.ConnectionService;
import tcejorptset.servl.service.ServiceException;

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

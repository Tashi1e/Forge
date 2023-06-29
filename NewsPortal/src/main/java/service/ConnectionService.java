package service;

public interface ConnectionService {
	
	void initPool() throws ServiceException;
	void closePool();

}

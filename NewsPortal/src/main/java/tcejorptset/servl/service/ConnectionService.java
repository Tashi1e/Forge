package tcejorptset.servl.service;

@Deprecated
public interface ConnectionService {
	
	void initPool() throws ServiceException;
	void closePool();

}

package service;

@SuppressWarnings("serial")
public class ServiceException extends Exception{

	public ServiceException(String e) {
		super(e);
	}
	
	public ServiceException(Exception e) {
		super(e);
	}
}

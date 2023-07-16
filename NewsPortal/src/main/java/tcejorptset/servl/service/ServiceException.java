package tcejorptset.servl.service;


public class ServiceException extends Exception {

	private static final long serialVersionUID = -3017979106026363030L;

	public ServiceException(String message) {
		super(message);
	}
	
	public ServiceException(Exception e) {
		super(e);
	}
	
	public ServiceException(String message, Exception e) {
		super(message, e);
	}
}

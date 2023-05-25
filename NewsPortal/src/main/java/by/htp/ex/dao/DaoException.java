package by.htp.ex.dao;

public class DaoException extends Exception {
	private static final long serialVersionUID = 8814453066415187129L;

	public DaoException() {
		super();
	}
	
	public DaoException(String message) {
		super(message);
	}
	
	public DaoException(Exception e) {
		super(e);
	}
	
	public DaoException(String message, Exception e) {
		super(message, e);
	}
}

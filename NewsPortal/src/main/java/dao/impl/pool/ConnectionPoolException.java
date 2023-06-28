package dao.impl.pool;

public class ConnectionPoolException extends Exception {
	
	private static final long serialVersionUID = -7251000565287359510L;

	public ConnectionPoolException(Exception e){
		super(e);
		}
	
	public ConnectionPoolException(String message){
		super(message);
		}
	
	public ConnectionPoolException(String message, Exception e){
		super(message, e);
		}

}

package tcejorptset.servl.listener;


import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener{

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		se.getSession().setAttribute("firstEnter", "yes");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		
	}

	
	
}

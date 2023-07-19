package tcejorptset.servl.controller.listener;


import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

public class LoginByTokenListener implements HttpSessionListener{

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		se.getSession().setAttribute("firstEnter", "yes");
		se.getSession().setAttribute("user_active", false);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		
	}

	
	
}

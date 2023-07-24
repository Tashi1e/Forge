package tcejorptset.servl.controller.listeners;


import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

public class LoginByTokenListener implements HttpSessionListener{
	
	private static final String JSP_USER_ACTIVE_ATTRIBUTE = "user_active";
	private static final String FIRST_TIME_ENTER_ATTRIBUTE = "firstEnter";

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		se.getSession().setAttribute(FIRST_TIME_ENTER_ATTRIBUTE, "yes");
		se.getSession().setAttribute(JSP_USER_ACTIVE_ATTRIBUTE, false);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		
	}

	
	
}

package tcejorptset.servl.controller.impl;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tcejorptset.servl.controller.Command;

public class DoChangeLocale implements Command {
	
	private final static String CURRENT_PAGE_URL_ATTRIBUTE = "pageURL";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String locale = request.getParameter(AttributeParamName.LOCALE_NAME_PARAM_ATTRIBUTE);
		String previousPageURL = (String) request.getSession().getAttribute(CURRENT_PAGE_URL_ATTRIBUTE);
		request.getSession(true).setAttribute(AttributeParamName.LOCALE_NAME_PARAM_ATTRIBUTE, locale);
		
		if (previousPageURL != null) {
			response.sendRedirect(previousPageURL);
		} else {
			response.sendRedirect("controller?command=go_to_base_page");
		}
	}
}

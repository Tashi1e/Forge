package tcejorptset.servl.controller.impl;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tcejorptset.servl.controller.Command;

public class DoChangeLocale implements Command {
	
	private static final String LOCALE_PARAM_NAME = "locale";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//FIXME change redirect way to URL
		String getLocale = request.getParameter(LOCALE_PARAM_NAME);
		request.getSession(true).setAttribute("locale", getLocale);
		String currentPageCommand = (String) request.getSession().getAttribute("currentPage");
		response.sendRedirect("controller?command="+currentPageCommand);
		
			
	}

}

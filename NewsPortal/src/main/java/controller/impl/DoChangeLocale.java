package controller.impl;

import java.io.IOException;

import controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoChangeLocale implements Command {
	
	private final String LOCALE_PARAM_NAME = "locale";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String getLocale = request.getParameter(LOCALE_PARAM_NAME);
		request.getSession(true).setAttribute("locale", getLocale);
		String currentPageCommand = (String) request.getSession().getAttribute("currentPage");
		response.sendRedirect("controller?command="+currentPageCommand);
		
			
	}

}

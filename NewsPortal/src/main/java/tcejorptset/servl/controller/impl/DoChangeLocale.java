package tcejorptset.servl.controller.impl;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tcejorptset.servl.controller.Command;

public class DoChangeLocale implements Command {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String locale = request.getParameter(AttributeParamName.LOCALE_NAME_PARAM_ATTRIBUTE);
		request.getSession(true).setAttribute(AttributeParamName.LOCALE_NAME_PARAM_ATTRIBUTE, locale);
		response.sendRedirect((String) request.getSession().getAttribute("pageURL"));
	}
}

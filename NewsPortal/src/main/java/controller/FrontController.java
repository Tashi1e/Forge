package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final String COMMAND_PARAM_NAME = "command";
	private final String LOCALE_PARAM_NAME = "local";
	
	private final CommandProvider provider = CommandProvider.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	private void process (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String commandName = request.getParameter(COMMAND_PARAM_NAME);
		String getLocale = request.getParameter(LOCALE_PARAM_NAME);
		
		if(getLocale!=null) {
			request.getSession(true).setAttribute("local", request.getParameter("local"));
		}
		
		Command command = provider.getCommand(commandName);
		command.execute(request, response);
	}

}

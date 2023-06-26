package controller;

import java.io.IOException;

import org.apache.commons.lang.RandomStringUtils;

import jakarta.servlet.http.Cookie;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.cookies.CookiesTestClass;


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
		
//		new CookiesTestClass().saveCookiesToAttributes(request, response);
//		String randomString = RandomStringUtils.randomAlphanumeric(32);
//		String sessionID = request.getSession(false).getId();
//		System.out.println(randomString);
//		Cookie c11 = new Cookie("JSESSIONID", sessionID);
//		response.addCookie(c11);
		
		
		Command command = provider.getCommand(commandName);
		command.execute(request, response);
	}

}

package controller;

import java.io.IOException;

import org.apache.commons.lang.RandomStringUtils;

import jakarta.servlet.http.Cookie;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ConnectionService;
import service.ServiceException;
import service.ServiceProvider;
import util.cookies.CookiesTestClass;


public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final String COMMAND_PARAM_NAME = "command";
	
	private final CommandProvider provider = CommandProvider.getInstance();
	
	private final ConnectionService connectionService = ServiceProvider.getInstance().getConnectionService();
	
	
	@Override
	public void init() throws ServletException {
		try {
			connectionService.initPool();
		} catch (ServiceException e) {
			throw new ServletException(e);
		}
		super.init();
	}
	
	@Override
	public void destroy() {
			connectionService.closePool();
		super.destroy();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	private void process (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String commandName = request.getParameter(COMMAND_PARAM_NAME);
		
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

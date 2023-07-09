package controller;

import java.io.IOException;

//import org.apache.commons.lang.RandomStringUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//GARBAGE
//import service.ConnectionService;
//import service.ServiceProvider;

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final String COMMAND_PARAM_NAME = "command";

	private final CommandProvider provider = CommandProvider.getInstance();
	
// GARBAGE	
//	private final ConnectionService connectionService = ServiceProvider.getInstance().getConnectionService();

//	@Override
//	public void init() throws ServletException {
//		try {
//			connectionService.initPool();
//			super.init();
//		} catch (ServiceException e) {
//			throw new ServletException(e);
//		}
//	}
//
//	@Override
//	public void destroy() {
//		connectionService.closePool();
//		super.destroy();
//	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String commandName = request.getParameter(COMMAND_PARAM_NAME);

		Command command = provider.getCommand(commandName);
		command.execute(request, response);
	}

}

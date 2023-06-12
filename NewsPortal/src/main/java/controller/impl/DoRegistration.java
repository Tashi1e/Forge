package controller.impl;

import java.io.IOException;

import controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.IUserService;
import service.ServiceProvider;

public class DoRegistration implements Command {
	
	private final IUserService service = ServiceProvider.getInstance().getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nickName = request.getParameter("nick");
		String email = request.getParameter("email");
		String country = request.getParameter("country");
		String phoneNumber = request.getParameter("tel");
		String password = request.getParameter("pass");
		

		
		
		
		response.sendRedirect("controller?command=go_to_main_page");
		
	}

}

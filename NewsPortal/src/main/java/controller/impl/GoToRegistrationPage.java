package controller.impl;

import java.io.IOException;

import controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToRegistrationPage implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		response.sendRedirect("/goToRegistrationPage");
		request.getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(request, response);
	}

}

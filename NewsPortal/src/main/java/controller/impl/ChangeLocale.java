package controller.impl;

import java.io.IOException;

import controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ChangeLocale implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getSession(true).setAttribute("local", request.getParameter("local"));
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}

}

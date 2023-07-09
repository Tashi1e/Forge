package controller.impl;

import java.io.IOException;

import controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoSignOut implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			request.getSession(true).setAttribute("user", "not active");
			request.getSession().removeAttribute("role");
			request.getSession().removeAttribute("userNickName");
			
//			response.sendRedirect("index.jsp"); GARBAGE
			response.sendRedirect("controller?command=go_to_base_page");
		
	}

}

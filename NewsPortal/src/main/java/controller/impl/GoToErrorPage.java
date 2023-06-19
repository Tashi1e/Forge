package controller.impl;

import java.io.IOException;
import java.util.List;

import bean.News;
import controller.Command;
import service.INewsService;
import service.ServiceException;
import service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToErrorPage implements Command{

	private String errorCode = new String();
	
	public GoToErrorPage (String errorCode) {
		this.errorCode=errorCode;
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			request.setAttribute("errorCode", errorCode);
			request.getRequestDispatcher("WEB-INF/pages/tiles/error.jsp").forward(request, response);
	}

}

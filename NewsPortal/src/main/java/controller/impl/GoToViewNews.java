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

@SuppressWarnings("unused")
public class GoToViewNews implements Command {
	
	private final INewsService newsService = ServiceProvider.getInstance().getNewsService();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		News news;
		
		String id;
		id = request.getParameter("id");
		
		if(id ==null) {
			id = (String) request.getSession(true).getAttribute("newsID");
		}
		else {
			request.getSession(true).setAttribute("newsID", id);	
		}
		
		try {
			news  = newsService.findById(Integer.parseInt(id));
			request.setAttribute("news", news);
			request.setAttribute("presentation", "viewNews");
			request.getSession(true).setAttribute("newsID", id);
			request.getSession().setAttribute("currentPage", request.getParameter("command"));

			request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
		} catch (ServiceException e) {
			response.sendRedirect("controller?command=go_to_news_list");
			e.printStackTrace();
		}
		
	}

}

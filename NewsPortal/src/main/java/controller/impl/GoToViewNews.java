package controller.impl;

import java.io.IOException;

import bean.News;
import controller.Command;
import service.INewsService;
import service.ServiceException;
import service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToViewNews implements Command {
	
	private final INewsService newsService = ServiceProvider.getInstance().getNewsService();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		News news;
		
		String id;
		id = request.getParameter("id");
		
		if(id ==null) {
			response.sendRedirect("controller?command=go_to_news_list");
		}
//GARBAGE		
//		else {
//			request.getSession(true).setAttribute(AttributeParamName.JSP_NEWS_ID_ATTRIBUTE, id);	
//		}
		
		try {
			news  = newsService.findById(Integer.parseInt(id));
			request.setAttribute(AttributeParamName.JSP_NEWS_ATTRIBUTE, news);
			request.setAttribute(AttributeParamName.JSP_PRESENTATION_ATTRIBUTE, "viewNews");
//			request.getSession(true).setAttribute(AttributeParamName.JSP_NEWS_ID_ATTRIBUTE, id); //GARBAGE
			request.getSession().setAttribute("currentPage", request.getParameter("command")); //FIXME!!!

			request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
		} catch (ServiceException e) {
			response.sendRedirect("controller?command=go_to_news_list");
			e.printStackTrace();
		}
		
	}

}

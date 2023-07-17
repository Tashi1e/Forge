package tcejorptset.servl.controller.impl;

import java.io.IOException;

import tcejorptset.servl.bean.News;
import tcejorptset.servl.bean.UserInfo;
import tcejorptset.servl.controller.Command;
import tcejorptset.servl.service.INewsService;
import tcejorptset.servl.service.ServiceException;
import tcejorptset.servl.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToViewNews implements Command {
	
	private final INewsService newsService = ServiceProvider.getInstance().getNewsService();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		News news;
		
		String id = request.getParameter("id"); 
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(AttributeParamName.JSP_USER_INFO_ATTRIBUTE);
		String author = userInfo.getFirstName() + " " + userInfo.getLastName();
		
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
			request.setAttribute(AttributeParamName.JSP_NEWS_AUTHOR_ATTRIBUTE, author); //GARBAGE
			request.getSession().setAttribute("currentPage", request.getParameter("command")); //FIXME!!!

			request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
		} catch (ServiceException e) {
			response.sendRedirect("controller?command=go_to_news_list");
			e.printStackTrace();
		}
		
	}

}

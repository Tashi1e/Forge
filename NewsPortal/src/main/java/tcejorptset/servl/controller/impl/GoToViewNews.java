package tcejorptset.servl.controller.impl;

import java.io.IOException;

import tcejorptset.servl.bean.News;
import tcejorptset.servl.bean.UserInfo;
import tcejorptset.servl.controller.Command;
import tcejorptset.servl.service.INewsService;
import tcejorptset.servl.service.IUserService;
import tcejorptset.servl.service.ServiceException;
import tcejorptset.servl.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToViewNews implements Command {
	
	private final INewsService newsService = ServiceProvider.getInstance().getNewsService();
	private final IUserService userService = ServiceProvider.getInstance().getUserService();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		News news;
		UserInfo userInfo;
		String author;
		
		String id = request.getParameter("id"); 
		
		if(id == null) {
			response.sendRedirect("controller?command=go_to_news_list");
			//TODO some error
		}
		
		try {
			news  = newsService.findById(Integer.parseInt(id));
			userInfo = userService.getUserInfo(news.getUserId());
			author = userInfo.getFirstName() + " " + userInfo.getLastName();
			
			request.setAttribute(AttributeParamName.JSP_NEWS_ATTRIBUTE, news);
			request.setAttribute(AttributeParamName.JSP_PRESENTATION_ATTRIBUTE, "viewNews");
			request.setAttribute(AttributeParamName.JSP_NEWS_AUTHOR_ATTRIBUTE, author);

			request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
		} catch (ServiceException e) {
			response.sendRedirect("controller?command=go_to_news_list");
			e.printStackTrace();
		}
		
	}

}

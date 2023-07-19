package tcejorptset.servl.controller.impl;

import java.io.IOException;

import tcejorptset.servl.bean.News;
import tcejorptset.servl.controller.Command;
import tcejorptset.servl.service.INewsService;
import tcejorptset.servl.service.ServiceException;
import tcejorptset.servl.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoEditNews implements Command {
	
	INewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int newsId = Integer.parseInt(request.getParameter("id"));
		
		String newsTitle = request.getParameter(AttributeParamName.JSP_NEWS_TITLE_PARAM);
		String newsBrief = request.getParameter(AttributeParamName.JSP_NEWS_BRIEF_PARAM);
		String newsContent = request.getParameter(AttributeParamName.JSP_NEWS_CONTENT_PARAM); 
		
		var news = new News();
		news.setId(newsId);
		news.setTitle(newsTitle);
		news.setBrief(newsBrief);
		news.setContent(newsContent);
		
			try {
				newsService.update(news);
				response.sendRedirect("controller?command=go_to_news_list");
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}

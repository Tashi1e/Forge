package tcejorptset.servl.controller.impl;

import java.io.IOException;

import tcejorptset.servl.bean.News;
import tcejorptset.servl.bean.UserInfo;
import tcejorptset.servl.controller.Command;
import tcejorptset.servl.service.INewsService;
import tcejorptset.servl.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoEditNews implements Command {
	
	INewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(AttributeParamName.JSP_USER_INFO_ATTRIBUTE);
		String newsTitle = request.getParameter(AttributeParamName.JSP_NEWS_TITLE_PARAM);
		String newsBrief = request.getParameter(AttributeParamName.JSP_NEWS_BRIEF_PARAM);
		String newsContent = request.getParameter(AttributeParamName.JSP_NEWS_CONTENT_PARAM);
//		String newsAuthor = userInfo.getFirstName() + " " + userInfo.getLastName();
		
		var news = new News();
		news.setTitle(newsTitle);
		news.setBrief(newsBrief);
		news.setContent(newsContent);
		
//		try {
//			newsService.save();
//		}
		
	}

}

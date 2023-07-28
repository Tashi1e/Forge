package tcejorptset.servl.controller.impl;

import java.io.IOException;

import tcejorptset.servl.bean.ErrorCode;
import tcejorptset.servl.bean.News;
import tcejorptset.servl.controller.Command;
import tcejorptset.servl.service.INewsService;
import tcejorptset.servl.service.ServiceException;
import tcejorptset.servl.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class DoEditNews implements Command {

	INewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Part ImagePart = request.getPart(AttributeParamName.JSP_NEWS_IMAGE_PART_PARAM);
		int newsId = Integer.parseInt(request.getParameter(AttributeParamName.JSP_NEWS_ID_PARAM));
		String newsTitle = request.getParameter(AttributeParamName.JSP_NEWS_TITLE_PARAM);
		String newsBrief = request.getParameter(AttributeParamName.JSP_NEWS_BRIEF_PARAM);
		String newsContent = request.getParameter(AttributeParamName.JSP_NEWS_CONTENT_PARAM);
		
		var news = new News();
		news.setId(newsId);
		news.setTitle(newsTitle);
		news.setBrief(newsBrief);
		news.setContent(newsContent);
		news.setImgPart(ImagePart);
		

		try {
			newsService.update(news);
			response.sendRedirect("controller?command=go_to_news_list");
		} catch (ServiceException e) {
			request.getSession().setAttribute(AttributeParamName.JSP_ERROR_CODE_ATTRIBUTE, ErrorCode.UPDATE_NEWS.getCode());
			response.sendRedirect("error?command=go_to_base_page");
			e.printStackTrace();
		}
	}

}

package tcejorptset.servl.controller.impl;

import java.io.IOException;
import java.util.stream.Stream;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tcejorptset.servl.bean.ErrorCode;
import tcejorptset.servl.controller.Command;
import tcejorptset.servl.service.INewsService;
import tcejorptset.servl.service.ServiceException;
import tcejorptset.servl.service.ServiceProvider;

public class DoDeleteNews implements Command {
	
	INewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String [] newsId = request.getParameterValues(AttributeParamName.JSP_NEWS_ID_PARAM);
			try {
				if(newsId != null) {
				int [] id = Stream.of(newsId)
						  .mapToInt(Integer::parseInt)
						  .toArray();
				newsService.delete(id);
				}
				response.sendRedirect("controller?command=go_to_news_list");
			} catch (ServiceException e) {
				request.getSession().setAttribute (AttributeParamName.JSP_ERROR_CODE_ATTRIBUTE, ErrorCode.DELETE_NEWS.getCode());
				response.sendRedirect("error?command=go_to_news_list");
				e.printStackTrace();
			}
		}
	}


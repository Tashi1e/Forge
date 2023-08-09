package com.tcejorptset.servl.controller.impl;

import java.io.IOException;
import java.util.stream.Stream;

import com.tcejorptset.servl.controller.Command;
import com.tcejorptset.servl.globalConstants.ErrorCode;
import com.tcejorptset.servl.service.INewsService;
import com.tcejorptset.servl.service.ServiceException;
import com.tcejorptset.servl.service.ServiceProvider;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoDeleteNews implements Command {
	
	private final INewsService newsService = ServiceProvider.getInstance().getNewsService();

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


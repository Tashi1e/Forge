package com.tcejorptset.servl.controller.impl;

import java.io.IOException;
import java.util.List;

import com.tcejorptset.servl.bean.ErrorCode;
import com.tcejorptset.servl.bean.News;
import com.tcejorptset.servl.controller.Command;
import com.tcejorptset.servl.service.INewsService;
import com.tcejorptset.servl.service.ServiceException;
import com.tcejorptset.servl.service.ServiceProvider;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToNewsList implements Command {

	private final INewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<News> newsList;
		String keyword = request.getParameter(AttributeParamName.JSP_NEWS_KEYWORD_PARAM);
		try {
			if (keyword != null) {
				newsList = newsService.find(keyword);
			} else {
				newsList = newsService.latestList();
			}
			request.setAttribute(AttributeParamName.JSP_NEWS_ATTRIBUTE, newsList);
			request.setAttribute(AttributeParamName.JSP_PRESENTATION_ATTRIBUTE, "newsList");
			request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
		} catch (ServiceException e) {
			request.setAttribute(AttributeParamName.JSP_ERROR_CODE_ATTRIBUTE, ErrorCode.LATEST_NEWS_LITS.getCode());
			response.sendRedirect("error?command=go_to_error_page");
			e.printStackTrace();
		}
	}
}

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

public class GoToBasePage implements Command {

	private final INewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<News> latestNews;
		try {
				latestNews = newsService.latestList();
				request.setAttribute(AttributeParamName.JSP_NEWS_ATTRIBUTE, latestNews);
				request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
		} catch (ServiceException e) {
			request.getSession().setAttribute(AttributeParamName.JSP_ERROR_CODE_ATTRIBUTE, ErrorCode.LATEST_NEWS_LITS.getCode());
			response.sendRedirect("error?command=go_to_error_page");
			e.printStackTrace();
		}

	}

}

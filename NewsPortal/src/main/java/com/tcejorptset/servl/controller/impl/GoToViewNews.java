package com.tcejorptset.servl.controller.impl;

import java.io.IOException;

import com.tcejorptset.servl.bean.News;
import com.tcejorptset.servl.bean.UserInfo;
import com.tcejorptset.servl.controller.Command;
import com.tcejorptset.servl.globalConstants.ErrorCode;
import com.tcejorptset.servl.service.INewsService;
import com.tcejorptset.servl.service.IUserService;
import com.tcejorptset.servl.service.ServiceException;
import com.tcejorptset.servl.service.ServiceProvider;

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

		String id = request.getParameter(AttributeParamName.JSP_NEWS_ID_PARAM);

		if (id == null) {
			request.getSession().setAttribute(AttributeParamName.JSP_ERROR_CODE_ATTRIBUTE, ErrorCode.FETCH_NEWS.getCode());
			response.sendRedirect("controller?command=go_to_error_page");
		}

		try {
			news = newsService.findById(Integer.parseInt(id));
			request.setAttribute(AttributeParamName.JSP_NEWS_ATTRIBUTE, news);
			request.setAttribute(AttributeParamName.JSP_PRESENTATION_ATTRIBUTE, "viewNews");

			userInfo = userService.getUserInfo(news.getUserId());
			author = userInfo.getFirstName() + " " + userInfo.getLastName();
			request.setAttribute(AttributeParamName.JSP_NEWS_AUTHOR_ATTRIBUTE, author);

			request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
		} catch (ServiceException e) {
			request.getSession().setAttribute(AttributeParamName.JSP_ERROR_CODE_ATTRIBUTE,
					ErrorCode.FETCH_NEWS.getCode());
			response.sendRedirect("controller?command=go_to_error_page");
			e.printStackTrace();
		}

	}

}

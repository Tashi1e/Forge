package com.tcejorptset.servl.controller.impl;

import java.io.IOException;

import com.tcejorptset.servl.bean.News;
import com.tcejorptset.servl.controller.Command;
import com.tcejorptset.servl.globalConstants.ErrorCode;
import com.tcejorptset.servl.service.INewsService;
import com.tcejorptset.servl.service.ServiceException;
import com.tcejorptset.servl.service.ServiceProvider;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToAddEditNewsPage implements Command {

	private final INewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		News news = new News();
		String presentation = request.getParameter(AttributeParamName.JSP_PRESENTATION_ATTRIBUTE);
		if (presentation.equals("editNews")) {
			try {
				int id  = Integer.parseInt(request.getParameter(AttributeParamName.JSP_NEWS_ID_PARAM)) ;
				news = newsService.findById(id);
			} catch (ServiceException e) {
				request.getSession().setAttribute(AttributeParamName.JSP_ERROR_CODE_ATTRIBUTE, ErrorCode.FETCH_NEWS.getCode());
				response.sendRedirect("error?command=go_to_error_page");
				e.printStackTrace();
			}
		}
		request.setAttribute(AttributeParamName.JSP_NEWS_ATTRIBUTE, news);
		request.setAttribute(AttributeParamName.JSP_PRESENTATION_ATTRIBUTE, presentation);
		request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);

	}

}

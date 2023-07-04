package controller.impl;

import java.io.IOException;
import java.util.List;

import bean.News;
import controller.Command;
import service.INewsService;
import service.ServiceException;
import service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.cookies.CookiesOps;

public class GoToBasePage implements Command {

	private final INewsService newsService = ServiceProvider.getInstance().getNewsService();
	

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<News> latestNews;
		try {
			CookiesOps cookiesOps = new CookiesOps();
			String selector = cookiesOps.findCookie(request, "selector");
			String validator = cookiesOps.findCookie(request, "validator");
			if (request.getSession() == null && selector != null && validator != null) {
				response.sendRedirect("controller?command=do_sign_in");
			} else {

				latestNews = newsService.latestList(5);
				request.setAttribute("news", latestNews);
				request.getSession(true).setAttribute("currentPage", request.getParameter("command"));
				request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
			}
		} catch (ServiceException e) {
			// loggin - error
			e.printStackTrace();
		}

	}

}

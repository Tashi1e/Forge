package tcejorptset.servl.controller.impl;

import java.io.IOException;
import java.util.List;

import tcejorptset.servl.bean.News;
import tcejorptset.servl.controller.Command;
import tcejorptset.servl.service.INewsService;
import tcejorptset.servl.service.ServiceException;
import tcejorptset.servl.service.ServiceProvider;
import tcejorptset.servl.util.cookies.CookiesOps;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToBasePage implements Command {

	private final INewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<News> latestNews;
		try {
			System.out.println("GoToBasePage -> first Enter   " + request.getSession().getAttribute("firstEnter")); //TEST
			
				latestNews = newsService.latestList(5);
				System.out.println(latestNews);
				request.setAttribute("news", latestNews);
				request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
				
		} catch (ServiceException e) {
			System.out.println("Base Page Error"); //TEST
			// loggin - error
			e.printStackTrace();
		}

	}

}

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
			CookiesOps cookiesOps = new CookiesOps();
			String selector = cookiesOps.findCookie(request, AttributeParamName.SELECTOR_PARAM);
			String validator = cookiesOps.findCookie(request, AttributeParamName.VALIDATOR_PARAM);
			
//			System.out.println("GoToBasePage -> Selector   " + selector); //TEST
//			System.out.println("GoToBasePage -> Validator   "+ validator);  //TEST
//			System.out.println("GoToBasePage -> first Enter   " + request.getSession().getAttribute("firstEnter")); //TEST
			
			if (request.getSession().getAttribute("firstEnter") != null) {
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

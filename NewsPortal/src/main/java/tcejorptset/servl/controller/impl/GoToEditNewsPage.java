package tcejorptset.servl.controller.impl;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tcejorptset.servl.bean.News;
import tcejorptset.servl.controller.Command;
import tcejorptset.servl.service.INewsService;
import tcejorptset.servl.service.ServiceException;
import tcejorptset.servl.service.ServiceProvider;

public class GoToEditNewsPage implements Command {

	private final INewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String presentation = request.getParameter(AttributeParamName.JSP_PRESENTATION_ATTRIBUTE);
		System.out.println(presentation);
		if (presentation.equals("editNews")) {
			try {
				int id  = Integer.parseInt(request.getParameter("id")) ;
				News news = newsService.findById(id);
				request.setAttribute(AttributeParamName.JSP_NEWS_ATTRIBUTE, news);
			} catch (ServiceException e) {
				// TODO Write error
				e.printStackTrace();
			}
			
		}

		request.setAttribute(AttributeParamName.JSP_PRESENTATION_ATTRIBUTE, presentation);
		request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);

	}

}

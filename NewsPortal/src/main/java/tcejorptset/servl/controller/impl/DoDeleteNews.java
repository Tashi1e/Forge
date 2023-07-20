package tcejorptset.servl.controller.impl;

import java.io.IOException;
import java.util.stream.Stream;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tcejorptset.servl.controller.Command;
import tcejorptset.servl.service.INewsService;
import tcejorptset.servl.service.ServiceException;
import tcejorptset.servl.service.ServiceProvider;

public class DoDeleteNews implements Command {
	
	INewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String [] newsId = request.getParameterValues("newsId");
		int [] id = Stream.of(newsId)
				  .mapToInt(Integer::parseInt)
				  .toArray();
		for (int s: id) {
		System.out.println(s);
		}
			try {
				newsService.delete(id);
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			response.sendRedirect("controller?command=go_to_news_list");
		}
	}


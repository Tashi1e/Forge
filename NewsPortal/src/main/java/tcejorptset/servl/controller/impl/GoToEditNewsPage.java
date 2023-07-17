package tcejorptset.servl.controller.impl;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tcejorptset.servl.controller.Command;

public class GoToEditNewsPage implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute(AttributeParamName.JSP_PRESENTATION_ATTRIBUTE, "editNews");
		request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
		
	}

}

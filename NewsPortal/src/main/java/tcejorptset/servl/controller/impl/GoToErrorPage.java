package tcejorptset.servl.controller.impl;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tcejorptset.servl.controller.Command;

public class GoToErrorPage implements Command{

	private String errorCode = new String();
	
	public GoToErrorPage (String errorCode) {
		this.errorCode=errorCode;
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			request.setAttribute(AttributeParamName.JSP_ERROR_CODE_ATTRIBUTE, errorCode);
			request.getRequestDispatcher("error.jsp").forward(request, response);
	}

}

package com.tcejorptset.servl.controller.impl;

import java.io.IOException;

import com.tcejorptset.servl.controller.Command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToErrorPage implements Command{
	
	private String errorCode;

	public GoToErrorPage (){}
	
	public GoToErrorPage (String errorCode){
		this.errorCode = errorCode;
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute(AttributeParamName.JSP_ERROR_CODE_ATTRIBUTE) == null) {
			request.getSession().setAttribute(AttributeParamName.JSP_ERROR_CODE_ATTRIBUTE, errorCode);
		} else {
			request.getSession().setAttribute(AttributeParamName.JSP_ERROR_CODE_ATTRIBUTE, "Unknown Error");
		}
			request.getRequestDispatcher("error.jsp").forward(request, response);
	}
}

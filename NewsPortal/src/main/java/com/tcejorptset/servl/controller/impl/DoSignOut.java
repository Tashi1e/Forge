package com.tcejorptset.servl.controller.impl;

import java.io.IOException;

import com.tcejorptset.servl.controller.Command;
import com.tcejorptset.servl.globalConstants.UserRole;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoSignOut implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			request.getSession(true).setAttribute(AttributeParamName.JSP_USER_ACTIVE_ATTRIBUTE, false);
			request.getSession().setAttribute(AttributeParamName.JSP_ROLE_ATTRIBUTE, UserRole.GUEST.getRole());
			request.getSession().removeAttribute(AttributeParamName.JSP_NICK_NAME_ATTRIBUTE);
			response.sendRedirect("controller?command=go_to_base_page");
		
	}

}

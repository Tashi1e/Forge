package tcejorptset.servl.controller.impl;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tcejorptset.servl.bean.UserRole;
import tcejorptset.servl.controller.Command;

public class DoSignOut implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			request.getSession(true).setAttribute(AttributeParamName.JSP_USER_ACTIVE_ATTRIBUTE, false);
			request.getSession().setAttribute(AttributeParamName.JSP_ROLE_ATTRIBUTE, UserRole.GUEST.getRole());
			request.getSession().removeAttribute(AttributeParamName.JSP_NICK_NAME_ATTRIBUTE);
			response.sendRedirect("controller?command=go_to_base_page");
		
	}

}

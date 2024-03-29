package com.tcejorptset.servl.controller.impl;

import java.io.IOException;

import com.tcejorptset.servl.bean.User;
import com.tcejorptset.servl.bean.UserInfo;
import com.tcejorptset.servl.controller.Command;
import com.tcejorptset.servl.globalConstants.ErrorCode;
import com.tcejorptset.servl.service.IUserService;
import com.tcejorptset.servl.service.ServiceException;
import com.tcejorptset.servl.service.ServiceProvider;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoRegistration implements Command {

	private final IUserService service = ServiceProvider.getInstance().getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String firstName;
		String lastName;
		String nickName;
		String email;
		String login;
		String password;

		firstName = request.getParameter(AttributeParamName.JSP_FIRST_NAME_PARAM);
		lastName = request.getParameter(AttributeParamName.JSP_LAST_NAME_PARAM);
		nickName = request.getParameter(AttributeParamName.JSP_NICK_NAME_ATTRIBUTE);
		email = request.getParameter(AttributeParamName.JSP_EMAIL_PARAM);
		login = request.getParameter(AttributeParamName.JSP_LOGIN_PARAM);
		password = request.getParameter(AttributeParamName.JSP_PASSWORD_PARAM);

		User user = new User();
		user.setLogin(login);
		user.setPassword(password);

		UserInfo userInfo = new UserInfo();
		userInfo.setFirstName(firstName);
		userInfo.setLastName(lastName);
		userInfo.setNickName(nickName);
		userInfo.setEmail(email);

		try {
			if (service.registration(user, userInfo))
				request.getSession().setAttribute(AttributeParamName.JSP_ERROR_CODE_ATTRIBUTE,
						ErrorCode.REGISTRATION_SUCCESSFUL.getCode());
			response.sendRedirect("error?command=go_to_base_page");
		} catch (ServiceException e) {
			if (e.getMessage() != null) {
				request.getSession().setAttribute(AttributeParamName.JSP_ERROR_CODE_ATTRIBUTE, e.getMessage().replaceFirst(".+: ", ""));
			} else {
				request.getSession().setAttribute(AttributeParamName.JSP_ERROR_CODE_ATTRIBUTE,
						ErrorCode.REGISTRATION_FAILED.getCode());
			}
			response.sendRedirect("error?command=go_to_base_page");
			e.printStackTrace();
		}

	}

}

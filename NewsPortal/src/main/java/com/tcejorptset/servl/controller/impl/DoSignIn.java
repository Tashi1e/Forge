package com.tcejorptset.servl.controller.impl;

import java.io.IOException;
import java.util.Map;

import com.tcejorptset.servl.bean.UserInfo;
import com.tcejorptset.servl.controller.Command;
import com.tcejorptset.servl.globalConstants.ErrorCode;
import com.tcejorptset.servl.globalConstants.UserRole;
import com.tcejorptset.servl.globalConstants.UserToken;
import com.tcejorptset.servl.service.IUserService;
import com.tcejorptset.servl.service.ServiceException;
import com.tcejorptset.servl.service.ServiceProvider;
import com.tcejorptset.servl.util.validation.UserDataValidation;
import com.tcejorptset.servl.util.validation.ValidationProvider;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoSignIn implements Command {
	
	private static final String FIRST_TIME_ENTER_ATTRIBUTE = "firstEnter";

	private final IUserService service = ServiceProvider.getInstance().getUserService();
	private final UserDataValidation userAuthValidation = ValidationProvider.getInstance().getUserDataValidation();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String role = UserRole.GUEST.getRole();
		UserInfo userInfo = null;
		String selector = null;
		String validator = null;

		if (request.getSession().getAttribute(FIRST_TIME_ENTER_ATTRIBUTE) != null) {
			selector = findCookie(request, UserToken.SELECTOR.getParam());
			validator = findCookie(request, UserToken.VALIDATOR.getParam());
		}

		try {
			if (selector != null && validator != null) {
				role = service.signInByToken(selector, validator);
				userInfo = service.getUserInfoByToken(selector, validator);
			}
		
			if (role != null && !role.equals(UserRole.GUEST.getRole())) {
				response = addCookie(response, service.updateUserToken(selector, validator));
			}
		} catch (ServiceException e) {
			// TODO only some logging, 
			//'cause no mater if you can't login by token for some reason, you can always login by regular way
			e.printStackTrace();
	}

			String login = request.getParameter(AttributeParamName.JSP_LOGIN_PARAM);
			String password = request.getParameter(AttributeParamName.JSP_PASSWORD_PARAM);
			boolean checkbox = request.getParameter(AttributeParamName.JSP_REMEMBER_ME_PARAM) == null ? false : true;

			try {
			if (userAuthValidation.checkAUthData(login, password)) {
				role = service.signIn(login, password);
				userInfo = service.getUserInfo(login, password);
			}
			if (role != null && !role.equals(UserRole.GUEST.getRole())) {
				if (checkbox) {
					response = addCookie(response, service.addUserToken(login, password));
				}
				signinSuccessful(request, response, role, userInfo);
			} else {
				signinFailed(request, response);
			}

		} catch (ServiceException e) {
				signinFailed(request, response);
		}
	}

	private void signinSuccessful(HttpServletRequest request, HttpServletResponse response, String role,
			UserInfo userInfo) throws ServletException, IOException {
		request.getSession().removeAttribute(FIRST_TIME_ENTER_ATTRIBUTE);
		request.getSession(true).setAttribute(AttributeParamName.JSP_USER_ACTIVE_ATTRIBUTE, true);
		request.getSession().setAttribute(AttributeParamName.JSP_ROLE_ATTRIBUTE, role);
		request.getSession().setAttribute(AttributeParamName.JSP_USER_INFO_ATTRIBUTE, userInfo);
		response.sendRedirect("controller?command=go_to_news_list");
	}

	private void signinFailed(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession(true).setAttribute(AttributeParamName.JSP_USER_ACTIVE_ATTRIBUTE, false);
		if (request.getSession().getAttribute(FIRST_TIME_ENTER_ATTRIBUTE) == null) {
			request.getSession().setAttribute(AttributeParamName.JSP_ERROR_CODE_ATTRIBUTE, ErrorCode.SIGN_IN.getCode());
		}
		request.getSession().removeAttribute(FIRST_TIME_ENTER_ATTRIBUTE);
		response.sendRedirect("error?command=go_to_base_page");
	}

	private HttpServletResponse addCookie(HttpServletResponse response, Map <String, String> token) {
		String tokenKey = token.get(UserToken.SELECTOR.getParam());
		String tokenValue = token.get(UserToken.VALIDATOR.getParam());
		response.addCookie(new Cookie(UserToken.SELECTOR.getParam(), tokenKey));
		response.addCookie(new Cookie(UserToken.VALIDATOR.getParam(), tokenValue));
		return response;
	}
	
	public String findCookie(HttpServletRequest request, String key) throws IOException {
		String result = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(key)) {
					result = cookie.getValue();
					break;
				}
			}
		}
		return result;
	}
}

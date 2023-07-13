package tcejorptset.servl.controller.impl;

import java.io.IOException;
import java.util.Map;

import tcejorptset.servl.bean.UserInfo;
import tcejorptset.servl.controller.Command;
import tcejorptset.servl.service.IUserService;
import tcejorptset.servl.service.ServiceException;
import tcejorptset.servl.service.ServiceProvider;
import tcejorptset.servl.util.cookies.CookiesOps;
import tcejorptset.servl.util.validation.UserDataValidation;
import tcejorptset.servl.util.validation.ValidationProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoSIgnIn implements Command {

	private final IUserService service = ServiceProvider.getInstance().getUserService();
	private final UserDataValidation userAuthValidation = ValidationProvider.getInstance().getUserDataValidation();

	private String role = null;
	private UserInfo userInfo = null;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		CookiesOps cookiesOps = new CookiesOps();
		String selector = cookiesOps.findCookie(request, AttributeParamName.SELECTOR_PARAM);
		String validator = cookiesOps.findCookie(request, AttributeParamName.VALIDATOR_PARAM);
		request.getSession().removeAttribute("firstEnter");

		if (selector != null && validator != null && request.getSession().getAttribute("firstEnter") != null) {
			try {

				role = service.signIn(selector, validator);
				userInfo = service.getUserInfo(selector, validator);
				signinSuccessful(request, response);
				request.getSession().removeAttribute("firstEnter");
			} catch (ServiceException e) {
				response.sendRedirect("controller?command=go_to_base_page");
				e.printStackTrace();
			}
		} else {

			String login = request.getParameter(AttributeParamName.JSP_LOGIN_PARAM);
			String password = request.getParameter(AttributeParamName.JSP_PASSWORD_PARAM);
			boolean checkbox = request.getParameter(AttributeParamName.JSP_REMEMBER_ME_PARAM) == null ? false : true;

			if (userAuthValidation.checkAUthData(login, password)) {

				try {

					role = service.signIn(login, password);
					userInfo = service.getUserInfo(login, password);

//					System.out.println("DoSignIn -> role  " + role); // TEST
//					System.out.println("DoSignIn -> Nickname  " + userNickName); // TEST

					if (checkbox) {
						Map<String, String> token = service.addUserToken(login, password);

						if (!role.equals("guest") && token != null) {
							String tokenKey = token.get(AttributeParamName.SELECTOR_PARAM);
							String tokenValue = token.get(AttributeParamName.VALIDATOR_PARAM);

//							System.out.println("DoSignIn -> tokenKey  " + tokenKey); // TEST
//							System.out.println("DoSignIn -> tokenValue  " + tokenValue); // TEST

							response.addCookie(new Cookie(AttributeParamName.SELECTOR_PARAM, tokenKey));
							response.addCookie(new Cookie(AttributeParamName.VALIDATOR_PARAM, tokenValue));

//							System.out.println("Cookie added"); //TEST
						}
					}

					if (role != null && !role.equals("guest")) {
						signinSuccessful(request, response);
					} else {
						signinFailed(request, response, "Wrong login or password!!!");
					}

				} catch (ServiceException e) {
					if (e.getMessage() != null) {
						signinFailed(request, response, e.getMessage());
					} else {
						signinFailed(request, response, "Wrong login or password!!!");
					}
				}

			} else {
				signinFailed(request, response, "Wrong login or password!!!");
			}
		}
	}

	private void signinSuccessful(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession(true).setAttribute(AttributeParamName.JSP_USER_ACTIVE_ATTRIBUTE, true);
		request.getSession().setAttribute(AttributeParamName.JSP_ROLE_ATTRIBUTE, role);
		request.getSession().setAttribute(AttributeParamName.JSP_USER_INFO_ATTRIBUTE, userInfo);
		response.sendRedirect("controller?command=go_to_news_list");
	}

	private void signinFailed(HttpServletRequest request, HttpServletResponse response, String message)
			throws ServletException, IOException {
		request.getSession(true).setAttribute(AttributeParamName.JSP_USER_ACTIVE_ATTRIBUTE, false);
		request.setAttribute("AuthenticationError", message);
		response.sendRedirect("controller?command=go_to_base_page");
	}
}

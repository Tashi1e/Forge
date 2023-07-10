package controller.impl;

import java.io.IOException;
import java.util.Map;

import controller.Command;
import controller.ControllerParameters;
import service.ServiceException;
import service.ServiceProvider;
import service.IUserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.validation.UserDataValidation;
import util.validation.ValidationProvider;
import util.cookies.CookiesOps;

public class DoSIgnIn implements Command {

	private final IUserService service = ServiceProvider.getInstance().getUserService();
	private final UserDataValidation userAuthValidation = ValidationProvider.getInstance().getUserDataValidation();

	private String role = null;
	private String userNickName = null;

// GARBAGE
//	private String selector;
//	private String validator;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		CookiesOps cookiesOps = new CookiesOps();
		String selector = cookiesOps.findCookie(request, ControllerParameters.SELECTOR_PARAM);
		String validator = cookiesOps.findCookie(request, ControllerParameters.VALIDATOR_PARAM);
		request.getSession().removeAttribute("firstEnter");

		if (selector != null && validator != null && request.getSession().getAttribute("firstEnter") != null) {
			try {

				role = service.signIn(selector, validator);
				userNickName = service.userNickName(selector, validator);
				signinSuccessful(request, response);
				request.getSession().removeAttribute("firstEnter");
			} catch (ServiceException e) {
				response.sendRedirect("controller?command=go_to_base_page");
				e.printStackTrace();
			}
		} else {

			String login = request.getParameter(ControllerParameters.JSP_LOGIN_PARAM);
			String password = request.getParameter(ControllerParameters.JSP_PASSWORD_PARAM);
			boolean checkbox = request.getParameter(ControllerParameters.JSP_REMEMBER_ME_PARAM) == null ? false : true;

			if (userAuthValidation.checkAUthData(login, password)) {

				try {

					role = service.signIn(login, password);
					userNickName = service.userNickName(login, password);

//					System.out.println("DoSignIn -> role  " + role); // TEST
//					System.out.println("DoSignIn -> Nickname  " + userNickName); // TEST

					if (checkbox) {
						Map<String, String> token = service.addUserToken(login, password);

						if (!role.equals("guest") && token != null) {
							String tokenKey = token.get(ControllerParameters.SELECTOR_PARAM);
							String tokenValue = token.get(ControllerParameters.VALIDATOR_PARAM);

//							System.out.println("DoSignIn -> tokenKey  " + tokenKey); // TEST
//							System.out.println("DoSignIn -> tokenValue  " + tokenValue); // TEST

							response.addCookie(new Cookie(ControllerParameters.SELECTOR_PARAM, tokenKey));
							response.addCookie(new Cookie(ControllerParameters.VALIDATOR_PARAM, tokenValue));

							System.out.println("Cookie added");
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
		request.getSession(true).setAttribute("user", "active");
		request.getSession().setAttribute("role", role);
		request.getSession().setAttribute("userNickName", userNickName);
		response.sendRedirect("controller?command=go_to_news_list");
	}

	private void signinFailed(HttpServletRequest request, HttpServletResponse response, String message)
			throws ServletException, IOException {
		request.getSession(true).setAttribute("user", "inactive");
		request.setAttribute("AuthenticationError", message);
		response.sendRedirect("controller?command=go_to_base_page");
	}
}

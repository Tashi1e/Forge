package controller.impl;

import java.io.IOException;

import controller.Command;
import controller.ControllerParameters;
import service.ServiceException;
import service.ServiceProvider;
import service.IUserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.encrypt.Encryptor;
import util.encrypt.HashEncryptor;
import util.validation.UserDataValidation;
import util.validation.ValidationProvider;
import util.cookies.CookiesOps;

public class DoSIgnIn implements Command {

	private final IUserService service = ServiceProvider.getInstance().getUserService();
	private final UserDataValidation userAuthValidation = ValidationProvider.getInstance().getUserDataValidation();

	private String role;
	private String userNickName;
	private String selector;
	private String validator;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CookiesOps cookiesOps = new CookiesOps();
		selector = cookiesOps.findCookie(request, ControllerParameters.SELECTOR_PARAM);
		validator = cookiesOps.findCookie(request, ControllerParameters.VALIDATOR_PARAM);
		
// REMOVE GARBAGE
//		selector = (String) request.getSession().getAttribute(ControllerParameters.SELECTOR_PARAM);
//		validator = (String) request.getSession().getAttribute(ControllerParameters.VALIDATOR_PARAM);
		
		if (selector != null & validator != null) {
			try {
				role = service.signIn(selector, validator);
				userNickName = service.userNickName(selector, validator);
				signinSuccessful(request, response);
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

					if (checkbox) {

						if (!role.equals("guest") && service.addUserToken(login, password)) {

							response.addCookie(new Cookie(ControllerParameters.SELECTOR_PARAM, selector));
							response.addCookie(new Cookie(ControllerParameters.VALIDATOR_PARAM, validator));
						}
					}
				} catch (ServiceException e) {
					if (e.getMessage() != null) {
						request.setAttribute("AuthenticationError", e.getMessage());
					} else {
						e.getMessage();
					}
				}

			} else {
				signinFailed(request, response, "Wrong login or password!!!");
			}

			if (role != null && !role.equals("guest")) {
				signinSuccessful(request, response);
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

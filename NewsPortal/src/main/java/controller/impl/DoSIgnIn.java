package controller.impl;

import java.io.IOException;

import controller.Command;
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
	private final CookiesOps cookiesOps = new CookiesOps();

	private static final String JSP_LOGIN_PARAM = "login";
	private static final String JSP_PASSWORD_PARAM = "password";
	private static final String SELECTOR_PARAM = "selector";
	private static final String VALIDATOR_PARAM = "validator";
	private static final String JSP_REMEMBER_ME_PARAM = "remember_me";
	
	

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String login = request.getParameter(JSP_LOGIN_PARAM);
		String password = request.getParameter(JSP_PASSWORD_PARAM);
		String selector = request.getParameter(SELECTOR_PARAM);
		String validator = request.getParameter(VALIDATOR_PARAM);
		boolean checkbox = request.getParameter(JSP_REMEMBER_ME_PARAM) == null ? false : true;
		
		

		if (userAuthValidation.checkAUthData(login, password)) {

			try {
				
				Encryptor encryptor = new HashEncryptor();
				String encLogin = encryptor.encrypt(login, "login");
				String encPassword = encryptor.encrypt(password, "password");
				
				String role = service.signIn(login, encPassword);
				String userNickName = service.userNickName(login);

				if (checkbox) {
					response.addCookie(new Cookie("selector", encLogin));
					response.addCookie(new Cookie("validator", encPassword));
				}
				
				if (!role.equals("guest")) {
					request.getSession(true).setAttribute("user", "active");
					request.getSession().setAttribute("role", role);
					request.getSession().setAttribute("userNickName", userNickName);
					response.sendRedirect("controller?command=go_to_news_list");
				} else {
					request.getSession(true).setAttribute("user", "inactive");
					request.setAttribute("AuthenticationError", "Wrong login or password!!!");
					response.sendRedirect("controller?command=go_to_base_page");
				}

			} catch (ServiceException e) {
				if (e.getMessage() != null) {
					request.setAttribute("AuthenticationError", e.getLocalizedMessage());
				} else {
					e.getLocalizedMessage();
				}
				// go-to error page
			}
		} else {
			request.getSession(true).setAttribute("user", "inactive");
			request.setAttribute("AuthenticationError", "Wrong login or password!!!");
			response.sendRedirect("controller?command=go_to_base_page");
		}

	}

}

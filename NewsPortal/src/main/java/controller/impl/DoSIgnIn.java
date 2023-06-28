package controller.impl;

import java.io.IOException;

import controller.Command;
import service.ServiceException;
import service.ServiceProvider;
import service.IUserService;
import service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("unused")
public class DoSIgnIn implements Command {

	private final IUserService service = ServiceProvider.getInstance().getUserService();

	private static final String JSP_LOGIN_PARAM = "login";
	private static final String JSP_PASSWORD_PARAM = "password";
	private static final String JSP_REMEMBER_ME_PARAM = "remember_me";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login;
		String password;
		boolean checkbox;
	

		login = request.getParameter(JSP_LOGIN_PARAM);
		password = request.getParameter(JSP_PASSWORD_PARAM);
		checkbox = request.getParameter(JSP_REMEMBER_ME_PARAM)==null?false:true;
		
//		System.out.println(checkbox);

		// small validation

		try {

			String role = service.signIn(login, password);
			String userNickName = service.userNickName(login, password);

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
			// logging e
			// go-to error page

		}

		// response.getWriter().print("do logination");

	}

}

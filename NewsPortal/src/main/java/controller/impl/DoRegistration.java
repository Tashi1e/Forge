package controller.impl;

import java.io.IOException;
import bean.UserInfo;

import controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.IUserService;
import service.ServiceException;
import service.ServiceProvider;

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

		firstName = request.getParameter("firstname");
		lastName = request.getParameter("lastname");
		nickName = request.getParameter("nickname");
		email = request.getParameter("email");
		login = request.getParameter("login");
		password = request.getParameter("password");

		UserInfo user = new UserInfo();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setNickName(nickName);
		user.setEmail(email);
		user.setLogin(login);
		user.setPassword(password);
		
//		System.out.println(user.toString());
		
		try {
			if (service.registration(user))
				request.getSession(true).setAttribute("message", "Thank You for registration");
			response.sendRedirect("controller?command=go_to_base_page");
		} catch (ServiceException e) {
			request.getSession(true).setAttribute("message", "Internal Error");
			response.sendRedirect("controller?command=go_to_base_page");
			e.printStackTrace();
		}

	}

}

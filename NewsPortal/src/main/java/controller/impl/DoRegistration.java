package controller.impl;

import java.io.IOException;
import bean.User;
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
				request.getSession(true).setAttribute("message", "Thank You for registration");
			response.sendRedirect("controller?command=go_to_base_page");
		} catch (ServiceException e) {
			request.getSession(true).setAttribute("message", "Registration failed");
			response.sendRedirect("controller?command=go_to_base_page");
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}

}

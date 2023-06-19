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
		
		String nickName;
		String email;
		String country;
		String phoneNumber;
		String password;

		nickName = request.getParameter("nick");
		email = request.getParameter("mail");
		country = request.getParameter("country");
		phoneNumber = request.getParameter("tel");
		password = request.getParameter("pass");

		UserInfo user = new UserInfo();
		user.setNickName(nickName);
		user.setEmail(email);
		user.setCountry(country);
		user.setPhoneNumber(phoneNumber);
		user.setPassword(password);
		user.setRole("user");
		
		System.out.println(user.toString());
		

//		user.setNickName("Tashile");
//		user.setEmail("blkkpr@narod.ru");
//		user.setCountry("Belarus");
//		user.setPhoneNumber("+375291301943");
//		user.setPassword("slk21kpr12");
//		user.setRole("admin");
		
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

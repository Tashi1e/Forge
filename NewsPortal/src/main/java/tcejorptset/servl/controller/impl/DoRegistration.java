package tcejorptset.servl.controller.impl;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tcejorptset.servl.bean.ErrorCode;
import tcejorptset.servl.bean.User;
import tcejorptset.servl.bean.UserInfo;
import tcejorptset.servl.controller.Command;
import tcejorptset.servl.service.IUserService;
import tcejorptset.servl.service.ServiceException;
import tcejorptset.servl.service.ServiceProvider;

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
						ErrorCode.REGISTRATION_SUCCESSFUL.name().toLowerCase());
			response.sendRedirect("controller?command=go_to_registration_page");
		} catch (ServiceException e) {
			if (e.getMessage() != null) {
				request.getSession().setAttribute(AttributeParamName.JSP_ERROR_CODE_ATTRIBUTE, e.getMessage());
			} else {
				request.getSession().setAttribute(AttributeParamName.JSP_ERROR_CODE_ATTRIBUTE,
						ErrorCode.REGISTRATION_FAILED.name().toLowerCase());
			}
			response.sendRedirect("error?command=go_to_registration_page");
			e.printStackTrace();
		}

	}

}

package util.cookies;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CookiesTestClass extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public void saveCookiesToAttributes(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			Map<String, String> cookieValues = new HashMap<String, String>();
			for (Cookie cookie : cookies) {
				cookieValues.put(cookie.getName(), cookie.getValue());
			}

			request.setAttribute("cookies", cookieValues);
		}
	}

}

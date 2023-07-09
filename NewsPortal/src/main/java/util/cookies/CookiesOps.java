package util.cookies;

import java.io.IOException;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class CookiesOps {

	public String findCookie(HttpServletRequest request, String key) throws IOException {
		String result = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(key)) {
					result = cookie.getValue();
					break;
				}
			}
		}
		return result;
	}
}

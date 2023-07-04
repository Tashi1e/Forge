package util.cookies;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.encrypt.Encryptor;
import util.encrypt.HashEncryptor;

public class CookiesOps {

	public Cookie getUserTokenCookie(String login, String password) {
		Encryptor encryptor = new HashEncryptor();
		String encryptedLogin = encryptor.encrypt(login, "selector");
		String ecryptedPassword = encryptor.encrypt(password, "validator");

		return new Cookie(encryptedLogin, ecryptedPassword);
	}


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

package tcejorptset.servl.controller.filters;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public final class RemoveErrorFilter extends HttpFilter {

	private static final long serialVersionUID = 4955833626067603819L;
	
	private final static String ERROR_CODE_ATTRIBUTE = "errorCode";
	

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

		if (request.getSession().getAttribute(ERROR_CODE_ATTRIBUTE) != null) {
			request.getSession().removeAttribute(ERROR_CODE_ATTRIBUTE);
		}
		chain.doFilter(request, response);
	}
}

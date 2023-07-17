package tcejorptset.servl.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public class LocaleFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String query = httpRequest.getQueryString();
		String pageURL = httpRequest.getRequestURL().toString()+"?"+query;
		
		if(query!=null && query.matches("(command=go_to)\\w+")) {
		httpRequest.getSession().setAttribute("pageURL", pageURL);
		}
		chain.doFilter(httpRequest, response);
	}
}

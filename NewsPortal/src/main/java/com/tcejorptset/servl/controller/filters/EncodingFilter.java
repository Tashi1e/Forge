package com.tcejorptset.servl.controller.filters;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public final class EncodingFilter extends HttpFilter {

	private static final long serialVersionUID = 4955833626067603819L;
	
	private final static String ENCODING = "utf-8";
	

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

			request.setCharacterEncoding(ENCODING);
			response.setCharacterEncoding(ENCODING);
		
			chain.doFilter(request, response);
	}
}

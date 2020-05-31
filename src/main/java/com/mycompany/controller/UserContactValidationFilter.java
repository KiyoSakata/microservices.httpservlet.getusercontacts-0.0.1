package com.mycompany.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

@WebFilter("/getusercontacts*")
public class UserContactValidationFilter implements Filter {
	private Logger logger = LoggerFactory.getLogger(UserContactValidationFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String id = req.getParameter("ID");
		String username = req.getParameter("Username");
		if (StringUtils.isBlank(id) && StringUtils.isBlank(username)) {
			res.sendError(HttpStatus.BAD_REQUEST.value(),
					"Missing parameter. API accepts one paramenter: User ID or Username");
			logger.error("Missing parameter. API accepts one paramenter: User ID or Username");
			return;
		}
		if (StringUtils.isNotBlank(id) && StringUtils.isNotBlank(username)) {
			res.sendError(HttpStatus.BAD_REQUEST.value(),
					"Ambiguous parameters. API accepts one parameter: User ID or Username");
			logger.error("Ambiguous parameters. API accepts one parameter: User ID or Username");
			return;
		}
		if (StringUtils.isNotBlank(id) && !NumberUtils.isCreatable(id)) {
			res.sendError(HttpStatus.BAD_REQUEST.value(), "Invalid Id");
			logger.error("Invalid Id");
			return;
		}

		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// close any resources here
	}

}

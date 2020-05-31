package com.mycompany.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.mycompany.model.UserContactModel;
import com.mycompany.service.UserContactService;
import com.mycompany.util.JsonConverter;

/**
 * GetUserContacts microservice.
 */
@WebServlet("/getusercontacts")
public class UserContactServlet extends HttpServlet {
	private static final long serialVersionUID = 4943042865434888676L;
	
	private Logger logger = LoggerFactory.getLogger(UserContactServlet.class);
	
	@Autowired
	private UserContactService userContactService;
	@Autowired
	private JsonConverter jsonConverter;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String id = request.getParameter("ID");
			String username = request.getParameter("Username");

			UserContactModel contact = null;
			if (StringUtils.isNotBlank(id)) {
				Long decodedId = Long.decode(id);
				logger.debug("Calling getUserContactById: {}", decodedId);
				contact = this.userContactService.getUserContactById(decodedId);
			} else if (StringUtils.isNotBlank(username)) {
				logger.debug("Calling getUserContactByUsername: {}", username);
				contact = this.userContactService.getUserContactByUsername(username);
			}
			this.sendAsJson(response, contact);
			logger.debug("Returning contact to requester.");
		} catch (NumberFormatException e) {
			response.sendError(HttpStatus.BAD_REQUEST.value(), "Invalid Id");
			logger.error("Invalid Id", e);
		} catch (Exception e) {
			response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name());
			logger.error(HttpStatus.INTERNAL_SERVER_ERROR.name(), e);
		}
	}

	/**
	 * Utility method to send object as JSON response
	 * 
	 * @param response
	 * @param contacts
	 * @throws Exception
	 */
	private void sendAsJson(HttpServletResponse response, Object contact) throws Exception {
		response.setContentType("application/json;charset=UTF-8");

		String result = this.jsonConverter.convertToJson(contact);
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
	}
}
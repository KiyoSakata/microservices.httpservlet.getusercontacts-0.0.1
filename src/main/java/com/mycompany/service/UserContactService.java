package com.mycompany.service;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.mycompany.controller.UserContactValidationFilter;
import com.mycompany.dto.UserDTO;
import com.mycompany.model.UserContactModel;
import com.mycompany.util.UserContactConverter;

@Service
public class UserContactService {
	private Logger logger = LoggerFactory.getLogger(UserContactValidationFilter.class);
	
	@Autowired
	private UserContactConverter converter;
	@Autowired
	private RestTemplate restTemplate;

	public UserContactModel getUserContactById(Long id) {
		ResponseEntity<UserDTO[]> result = requestUserResources();

		UserContactModel contact = Arrays.stream(result.getBody())
				.filter(user -> user.getId().equals(id))
				.map(user -> this.converter.convert(user))
				.findFirst()
				.orElse(this.getNotFoundUserContact());
		
		logger.debug("Searched for Id: {}. Retrieved id: {}", id, contact.getId());
		return contact;
	}

	public UserContactModel getUserContactByUsername(String username) {
		ResponseEntity<UserDTO[]> result = requestUserResources();

		UserContactModel contact = Arrays.stream(result.getBody())
				.filter(user -> user.getUsername().equals(username))
				.map(user -> this.converter.convert(user))
				.findFirst()
				.orElse(this.getNotFoundUserContact());

		logger.debug("Searched for Username: {}. Retrieved id: {}", username, contact.getId());
		return contact;
	}
	
	protected UriComponents getUserUri() {
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.scheme("https")
				.host("jsonplaceholder.typicode.com")
				.path("users")
				.build();
		return uriComponents;
	}


	protected ResponseEntity<UserDTO[]> requestUserResources() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<UserDTO[]> result = this.restTemplate.exchange(this.getUserUri().toUriString(), HttpMethod.GET, entity, UserDTO[].class);
		return result;
	}

	protected UserContactModel getNotFoundUserContact() {
		UserContactModel contact = new UserContactModel(-1L, null, null);
		return contact;
	}
}
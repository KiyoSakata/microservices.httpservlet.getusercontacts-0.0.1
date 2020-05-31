package com.mycompany.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.mycompany.config.GetUserContactApplication;
import com.mycompany.model.UserContactModel;

/**
 * Integration tests for GetContact microservice.
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = GetUserContactApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
@DirtiesContext
public class GetContactServletTests {

	@Value("${local.server.port}")
	private int port;
	@Value("${security.user.name}")
	private String username;
	@Value("${security.user.password}")
	private String password;

	@Test
	public void testResourceIsSecure() throws Exception {
		Map<String, String> urlVariables = new HashMap<>();
		urlVariables.put("id", "1");

		UriComponents uriComponents = this.getContactByIdUri(urlVariables);
		ResponseEntity<String> entity = new TestRestTemplate().getForEntity(uriComponents.toUriString(), String.class);

		assertEquals(HttpStatus.UNAUTHORIZED, entity.getStatusCode());
	}

	@Test
	public void testGetContactById() throws Exception {
		Map<String, String> urlVariables = new HashMap<>();
		urlVariables.put("id", "1");

		UriComponents uriComponents = this.getContactByIdUri(urlVariables);
		ResponseEntity<UserContactModel> entity = new TestRestTemplate(this.getUsername(), this.getPassword())
				.getForEntity(uriComponents.toUriString(), UserContactModel.class);

		assertEquals(HttpStatus.OK, entity.getStatusCode());
		assertNotNull(entity.getBody());
		assertEquals(entity.getBody().getId(), Long.valueOf(1L));
	}

	@Test
	public void testGetContactByUsername() throws Exception {
		Map<String, String> urlVariables = new HashMap<>();
		urlVariables.put("user", "Bret");

		UriComponents uriComponents = this.getContactByUsernameUri(urlVariables);
		ResponseEntity<UserContactModel> entity = new TestRestTemplate(this.getUsername(), this.getPassword())
				.getForEntity(uriComponents.toUriString(), UserContactModel.class);

		assertEquals(HttpStatus.OK, entity.getStatusCode());
		assertNotNull(entity.getBody());
		assertEquals(entity.getBody().getId(), Long.valueOf(1L));
	}

	@Test
	public void testGetContactByIdNotFound() throws Exception {
		Map<String, String> urlVariables = new HashMap<>();
		urlVariables.put("id", "0");

		UriComponents uriComponents = this.getContactByIdUri(urlVariables);
		ResponseEntity<UserContactModel> entity = new TestRestTemplate(this.getUsername(), this.getPassword())
				.getForEntity(uriComponents.toUriString(), UserContactModel.class);

		assertEquals(HttpStatus.OK, entity.getStatusCode());
		assertNotNull(entity.getBody());
		assertEquals(entity.getBody().getId(), Long.valueOf(-1L));
	}

	@Test
	public void testGetContactByUsernameNotFound() throws Exception {
		Map<String, String> urlVariables = new HashMap<>();
		urlVariables.put("user", "Kiyo");

		UriComponents uriComponents = this.getContactByUsernameUri(urlVariables);
		ResponseEntity<UserContactModel> entity = new TestRestTemplate(this.getUsername(), this.getPassword())
				.getForEntity(uriComponents.toUriString(), UserContactModel.class);

		assertEquals(HttpStatus.OK, entity.getStatusCode());
		assertNotNull(entity.getBody());
		assertEquals(entity.getBody().getId(), Long.valueOf(-1L));
	}

	@Test
	public void testGetContactByInvalidId() throws Exception {
		Map<String, String> urlVariables = new HashMap<>();
		urlVariables.put("id", "Kiyo");

		UriComponents uriComponents = this.getContactByIdUri(urlVariables);
		ResponseEntity<String> entity = new TestRestTemplate(this.getUsername(), this.getPassword())
				.getForEntity(uriComponents.toUriString(), String.class);

		assertEquals(HttpStatus.BAD_REQUEST, entity.getStatusCode());
	}
	
	@Test
	public void testGetContactByInvalidRequest() throws Exception {
		Map<String, String> urlVariables = new HashMap<>();
		urlVariables.put("id", "1");
		urlVariables.put("user", "Bret");
		
		UriComponents uriComponents = this.getContactByIdAndUsernameUri(urlVariables);
		ResponseEntity<String> entity = new TestRestTemplate(this.getUsername(), this.getPassword())
				.getForEntity(uriComponents.toUriString(), String.class);

		assertEquals(HttpStatus.BAD_REQUEST, entity.getStatusCode());
	}
	
	@Test
	public void testGetContactNoParameter() throws Exception {
		UriComponents uriComponents = this.getContactUriBuilder().build();
		ResponseEntity<String> entity = new TestRestTemplate(this.getUsername(), this.getPassword())
				.getForEntity(uriComponents.toUriString(), String.class);

		assertEquals(HttpStatus.BAD_REQUEST, entity.getStatusCode());
	}

	private UriComponents getContactByIdUri(Map<String, String> urlVariables) {
		UriComponents uriComponents = this.getContactUriBuilder()
				.query("ID={id}")
				.buildAndExpand(urlVariables);
		return uriComponents;
	}

	private UriComponents getContactByUsernameUri(Map<String, String> urlVariables) {
		UriComponents uriComponents = this.getContactUriBuilder()
				.query("Username={user}")
				.buildAndExpand(urlVariables);
		return uriComponents;
	}
	
	private UriComponents getContactByIdAndUsernameUri(Map<String, String> urlVariables) {
		UriComponents uriComponents = this.getContactUriBuilder()
				.query("ID={id}")
				.query("Username={user}")
				.buildAndExpand(urlVariables);
		return uriComponents;
	}

	private UriComponentsBuilder getContactUriBuilder() {
		UriComponentsBuilder uriComponents = UriComponentsBuilder.newInstance()
				.scheme("http")
				.host("localhost")
				.port(this.getPort())
				.path("getusercontacts");
		return uriComponents;	
	}

	private String getPassword() {
		return this.password;
	}

	private int getPort() {
		return port;
	}

	private String getUsername() {
		return username;
	}
}

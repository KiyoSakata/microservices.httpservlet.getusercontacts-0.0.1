package com.mycompany.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "email", "phone" })
public class UserContactModel {
	private Long id;
	private String email;
	private String phone;

	public UserContactModel(Long id, String email, String phone) {
		super();
		this.id = id;
		this.email = email;
		this.phone = phone;
	}

	public UserContactModel() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}

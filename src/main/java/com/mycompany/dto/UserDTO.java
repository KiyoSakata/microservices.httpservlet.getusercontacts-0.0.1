package com.mycompany.dto;

public class UserDTO {
	private Long id;
	private String name;
	private String username;
	private String email;
	private AddressDTO AddressObject;
	private String phone;
	private String website;
	private CompanyDTO CompanyObject;

	// Getter Methods

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public AddressDTO getAddress() {
		return AddressObject;
	}

	public String getPhone() {
		return phone;
	}

	public String getWebsite() {
		return website;
	}

	public CompanyDTO getCompany() {
		return CompanyObject;
	}

	// Setter Methods

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAddress(AddressDTO addressObject) {
		this.AddressObject = addressObject;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public void setCompany(CompanyDTO companyObject) {
		this.CompanyObject = companyObject;
	}
}
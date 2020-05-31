package com.mycompany.dto;

public class CompanyDTO {
	private String name;
	private String catchPhrase;
	private String bs;

	// Getter Methods

	public String getName() {
		return name;
	}

	public String getCatchPhrase() {
		return catchPhrase;
	}

	public String getBs() {
		return bs;
	}

	// Setter Methods

	public void setName(String name) {
		this.name = name;
	}

	public void setCatchPhrase(String catchPhrase) {
		this.catchPhrase = catchPhrase;
	}

	public void setBs(String bs) {
		this.bs = bs;
	}
}

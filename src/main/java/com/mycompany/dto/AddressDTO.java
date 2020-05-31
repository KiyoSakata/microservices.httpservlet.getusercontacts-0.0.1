package com.mycompany.dto;

public class AddressDTO {
	private String street;
	private String suite;
	private String city;
	private String zipcode;
	private GeoDTO GeoObject;

	// Getter Methods

	public String getStreet() {
		return street;
	}

	public String getSuite() {
		return suite;
	}

	public String getCity() {
		return city;
	}

	public String getZipcode() {
		return zipcode;
	}

	public GeoDTO getGeo() {
		return GeoObject;
	}

	// Setter Methods

	public void setStreet(String street) {
		this.street = street;
	}

	public void setSuite(String suite) {
		this.suite = suite;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public void setGeo(GeoDTO geoObject) {
		this.GeoObject = geoObject;
	}
}

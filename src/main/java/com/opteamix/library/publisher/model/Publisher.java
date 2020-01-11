package com.opteamix.library.publisher.model;

public class Publisher {

	private String publisherId;
	private String name;
	private String email;
	private String phoneNumber;

	public Publisher() {
		
	}
	
	public Publisher(String publisherId, String name, String email, String phoneNumber) {
		this.publisherId = publisherId;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public String getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(String publisherId) {
		this.publisherId = publisherId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}

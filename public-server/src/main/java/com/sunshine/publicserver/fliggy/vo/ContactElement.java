package com.sunshine.publicserver.fliggy.vo;

public class ContactElement {

	private String name;
	private String address;
	private String postcode;
	private String email;
	private String mobile;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "ContactElement [name=" + name + ", address=" + address
				+ ", postcode=" + postcode + ", email=" + email + ", mobile="
				+ mobile + "]";
	}

}

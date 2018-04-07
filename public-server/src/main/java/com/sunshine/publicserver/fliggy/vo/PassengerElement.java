package com.sunshine.publicserver.fliggy.vo;

public class PassengerElement {

	private String name;
	private int ageType;
	private String birthday;
	private String gender;
	private String cardNum;
	private String cardType;
	private String cardIssuePlace;
	private String cardExpired;
	private String nationality;
	private String mobile;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAgeType() {
		return ageType;
	}

	public void setAgeType(int ageType) {
		this.ageType = ageType;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardIssuePlace() {
		return cardIssuePlace;
	}

	public void setCardIssuePlace(String cardIssuePlace) {
		this.cardIssuePlace = cardIssuePlace;
	}

	public String getCardExpired() {
		return cardExpired;
	}

	public void setCardExpired(String cardExpired) {
		this.cardExpired = cardExpired;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "PassengerElement [name=" + name + ", ageType=" + ageType
				+ ", birthday=" + birthday + ", gender=" + gender
				+ ", cardNum=" + cardNum + ", cardType=" + cardType
				+ ", cardIssuePlace=" + cardIssuePlace + ", cardExpired="
				+ cardExpired + ", nationality=" + nationality + ", mobile="
				+ mobile + "]";
	}

}

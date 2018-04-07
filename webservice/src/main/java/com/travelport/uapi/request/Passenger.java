package com.travelport.uapi.request;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Passenger {
    private XMLGregorianCalendar birthDay;
    private String gender;
    private String id;  //key
    private String type;
    private String firstName;
    private String lastName;
    private String nike;    //prifix
    private String freeText;//护照信息
    private String emailId;//邮箱
    private String emailType;
    private BigInteger age;
    public XMLGregorianCalendar getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) throws ParseException, DatatypeConfigurationException {
        GregorianCalendar c = new GregorianCalendar();
        Date date = new Date();
        date = new SimpleDateFormat("yyyy-MM-dd").parse(birthDay);
        c.setTime(date);
        this.birthDay =DatatypeFactory.newInstance().newXMLGregorianCalendarDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH), DatatypeConstants.FIELD_UNDEFINED);;
    }

    public BigInteger getAge() {
        return age;
    }

    public void setAge(BigInteger age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNike() {
        return nike;
    }

    public void setNike(String nike) {
        this.nike = nike;
    }

    public String getFreeText() {
        return freeText;
    }

    public void setFreeText(String freeText) {
        this.freeText = freeText;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getEmailType() {
        return emailType;
    }

    public void setEmailType(String emailType) {
        this.emailType = emailType;
    }

    public Passenger(String birthDay, String gender, String id, String type, String firstName, String lastName, String nike, String freeText, String emailId, String emailType) throws ParseException, DatatypeConfigurationException {
        setBirthDay(birthDay);
        this.gender = gender;
        this.id = id;
        this.type = type;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nike = nike;
        this.freeText = freeText;
        this.emailId = emailId;
        this.emailType = emailType;
    }

    public Passenger() {
    }


}

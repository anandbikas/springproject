package com.anand.springproject.core.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 */
public class Student {

    public final static String
            ROLL_NUMBER="roll_number",
            FIRST_NAME="first_name",
            LAST_NAME="last_name",
            CONTACT="contact",
            ADDRESS="address",
            CITY="city";

    @JsonProperty("roll_number")
    private int rollNumber;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("contact")
    private String contact;

    @JsonProperty("address")
    private String address;

    @JsonProperty("city")
    private String city;

    public int getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


}

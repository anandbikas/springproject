package com.anand.springproject.core.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

/**
 *
 */
@Entity
@Table(name = "user")
public class User {

    public final static String
            ID="id",
            FIRST_NAME="first_name",
            LAST_NAME="last_name",
            EMAIL="email";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty(ID)
    private int id;

    @JsonProperty(FIRST_NAME)
    private String firstName;

    @JsonProperty(LAST_NAME)
    private String lastName;

    @JsonProperty(EMAIL)
    private String email;

    public static String getID() {
        return ID;
    }

    public static String getFirstName() {
        return FIRST_NAME;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public static String getLastName() {
        return LAST_NAME;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static String getEMAIL() {
        return EMAIL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

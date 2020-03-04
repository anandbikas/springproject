package com.anand.springproject.core.domain;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.StringJoiner;

/**
 *
 */
@Entity
@Table(name = "user")
@JsonPropertyOrder({User.ID, User.FIRST_NAME, User.LAST_NAME, User.EMAIL })
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
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

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("firstName='" + firstName + "'")
                .add("lastName='" + lastName + "'")
                .add("email='" + email + "'")
                .toString();
    }
}

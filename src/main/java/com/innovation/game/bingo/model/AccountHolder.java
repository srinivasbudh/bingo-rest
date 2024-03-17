package com.innovation.game.bingo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Document
public class AccountHolder {
    @Id
    @JsonProperty(required = true)
    private String username;

    @JsonProperty(required = true)
    private String password;
    @JsonProperty(required = true)
    private String firstname;
    @JsonProperty(required = true)
    private String lastname;
    @JsonProperty
    private String mobileNumber;



    @JsonProperty
    private float balance;


    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobilenumber) {
        this.mobileNumber = mobilenumber;
    }
}

package com.example.BlogApplication.Payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public class UserDto implements Serializable {

    private long id;

    @NotEmpty(message = "name should not be empty")
    @Size(min = 3, max =10,message ="name at least containing 3 characters !!")
    private String name;

    @Email(message = "invalid user email address !!")
    private String email;

    @NotEmpty(message="password should not be empty !!")
    @Size(min = 3,max = 12,message = "password must be at least 3 character and max 10 characters !!")
    private String password;

    private String about;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}

package com.SoftUni.DriverServiceProject.Models.DTO;

import jakarta.validation.constraints.NotEmpty;

public class UserRegistrationDTO {
    @NotEmpty(message = "Field could not be empty!")
    private String firstName;
    @NotEmpty(message = "Field could not be empty")
    private String lastName;
    @NotEmpty(message = "Field could not be empty")
    private String email;
    @NotEmpty(message = "Field could not be empty")
    private String password;
    @NotEmpty(message = "Field could not be empty")
    private String confirmPassword;

    public UserRegistrationDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public UserRegistrationDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRegistrationDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegistrationDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegistrationDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}

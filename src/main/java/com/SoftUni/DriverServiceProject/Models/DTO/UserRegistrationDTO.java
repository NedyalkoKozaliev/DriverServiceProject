package com.SoftUni.DriverServiceProject.Models.DTO;

import com.SoftUni.DriverServiceProject.ValidationAnotations.annotation.FieldMatcher;
import com.SoftUni.DriverServiceProject.ValidationAnotations.annotation.UniqueEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@FieldMatcher(
        first = "password",
        second ="confirmPassword",
        message = "Passwords should match."

)
public class UserRegistrationDTO {
    @NotEmpty(message = "Field could not be empty!")
    @Size(min=3, max=20, message = "First name length must be between 3 and 20 characters.")
    private String firstName;
    @Size(min=3, max=20, message = "Last name length must be between 3 and 20 characters.")
    @NotEmpty(message = "Field could not be empty")
    private String lastName;
    @UniqueEmail
    @NotEmpty(message = "Field could not be empty")
    @Email(message = "Please provide valid email address.")
    private String email;
    @Size(min=3, max=20, message = "Password length must be between 3 and 20 characters.")
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

package com.SoftUni.DriverServiceProject.Models.DTO;

import com.SoftUni.DriverServiceProject.ValidationAnotations.annotation.FieldMatcher;
import com.SoftUni.DriverServiceProject.ValidationAnotations.annotation.UniqueEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@FieldMatcher(
        first = "password",
        second ="confirmPassword",
        message = "Passwords should match."

)
public class DriverAddBindingModel {

        @NotEmpty(message = "Field could not be empty!")
        @Size(min=3, max=20, message = "First name length must be between 3 and 20 characters.")
        private String firstName;

        @NotEmpty(message = "Field could not be empty")
        @Size(min=3, max=20, message = "Last name length must be between 3 and 20 characters.")
        private String lastName;

        @UniqueEmail
        @NotEmpty(message = "Field could not be empty")
        @Email(message = "Please provide valid email address.")
        private String email;

        @NotEmpty(message = "Field could not be empty")
        @Size(min=3, max=20, message = "Password length must be between 3 and 20 characters.")
        private String password;

        @NotEmpty(message = "Field could not be empty")
        private String confirmPassword;

        @NotBlank(message="Registration must be provided.")
        private String registration;

    public DriverAddBindingModel() {
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }
}

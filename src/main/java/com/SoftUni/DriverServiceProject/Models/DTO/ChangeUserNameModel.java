package com.SoftUni.DriverServiceProject.Models.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class ChangeUserNameModel {

    private Long userId;
    private String confirmationEmail;
    private String newEmail;

    public ChangeUserNameModel() {
    }

    public Long getUserId() {
        return userId;
    }

    public ChangeUserNameModel setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    @Email
    @NotNull
    public String getConfirmationEmail() {
        return confirmationEmail;
    }

    public ChangeUserNameModel setConfirmationEmail(String confirmationEmail) {
        this.confirmationEmail = confirmationEmail;
        return this;
    }

    @Email
    @NotNull
    public String getNewEmail() {
        return newEmail;
    }

    public ChangeUserNameModel setNewEmail(String newEmail) {
        this.newEmail = newEmail;
        return this;
    }
}

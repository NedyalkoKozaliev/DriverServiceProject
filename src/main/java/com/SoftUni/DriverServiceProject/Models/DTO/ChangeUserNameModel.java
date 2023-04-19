package com.SoftUni.DriverServiceProject.Models.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class ChangeUserNameModel {

    private Long userId;
    private String currentEmail;
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
    public String getCurrentEmail() {
        return currentEmail;
    }

    public ChangeUserNameModel setCurrentEmail(String currentEmail) {
        this.currentEmail = currentEmail;
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

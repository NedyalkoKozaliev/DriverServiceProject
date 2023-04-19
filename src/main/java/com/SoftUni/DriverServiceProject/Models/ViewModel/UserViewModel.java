package com.SoftUni.DriverServiceProject.Models.ViewModel;

public class UserViewModel {
    private Long id;

    private String firstName;


    private String lastName;


    private String email;


    private String password;

    public UserViewModel() {
    }

    public Long getId() {
        return id;
    }

    public UserViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserViewModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserViewModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserViewModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserViewModel setPassword(String password) {
        this.password = password;
        return this;
    }
}

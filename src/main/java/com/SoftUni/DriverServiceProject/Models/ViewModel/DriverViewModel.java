package com.SoftUni.DriverServiceProject.Models.ViewModel;



public class DriverViewModel {


   private Long id;

    private String firstName;


    private String lastName;


    private String email;


    private String password;

    public DriverViewModel() {
    }

    public Long getId() {
        return id;
    }

    public DriverViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public DriverViewModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public DriverViewModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public DriverViewModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public DriverViewModel setPassword(String password) {
        this.password = password;
        return this;
    }
}

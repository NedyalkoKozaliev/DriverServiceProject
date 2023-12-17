package com.SoftUni.DriverServiceProject.Models.ServiceModels;

import com.SoftUni.DriverServiceProject.Models.Entity.Car;

public class DriverServiceModel {

    private String firstName;


    private String lastName;


    private String email;


    private String password;

    private Car car;

    public DriverServiceModel() {
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

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}

package com.SoftUni.DriverServiceProject.Models.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class GarageAddBindingModel {
    @NotBlank(message="Could not be blank!")
    @Size(message="Address length must be between 5 and 50 characters.")
    private String address;

    public GarageAddBindingModel() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

package com.SoftUni.DriverServiceProject.Models.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class GarageAddBindingModel {
    @NotBlank
    @Size(min=5,max = 50)
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

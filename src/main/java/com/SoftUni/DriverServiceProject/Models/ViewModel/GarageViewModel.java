package com.SoftUni.DriverServiceProject.Models.ViewModel;

public class GarageViewModel {

    private Long id;
    private String address;

    public GarageViewModel() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

package com.SoftUni.DriverServiceProject.Models.ServiceModels;

import com.SoftUni.DriverServiceProject.Models.Entity.Discount;
import com.SoftUni.DriverServiceProject.Models.Entity.User;
import jakarta.persistence.Column;

public class OrderServiceModel {

    private Long Id;
    private String addressFrom;

    private String addressTo;


    private Integer numberOfPassengers;

    private User client;

    public OrderServiceModel() {
    }

    public Long getId() {
        return Id;
    }

    public OrderServiceModel setId(Long id) {
        Id = id;
        return this;
    }

    public String getAddressFrom() {
        return addressFrom;
    }

    public OrderServiceModel setAddressFrom(String addressFrom) {
        this.addressFrom = addressFrom;
        return this;
    }

    public String getAddressTo() {
        return addressTo;
    }

    public OrderServiceModel setAddressTo(String addressTo) {
        this.addressTo = addressTo;
        return this;
    }

    public Integer getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public OrderServiceModel setNumberOfPassengers(Integer numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
        return this;
    }

    public User getClient() {
        return client;
    }

    public OrderServiceModel setClient(User client) {
        this.client = client;
        return this;
    }
}


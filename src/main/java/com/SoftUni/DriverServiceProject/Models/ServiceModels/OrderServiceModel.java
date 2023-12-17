package com.SoftUni.DriverServiceProject.Models.ServiceModels;

import com.SoftUni.DriverServiceProject.Models.Entity.User;

public class OrderServiceModel {

    private Long id;
    private String addressFrom;

    private String addressTo;


    private Integer numberOfPassengers;

    private User client;

    private Float distance;

    public OrderServiceModel() {
    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

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

    public void setClient(User client) {
        this.client = client;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }
}


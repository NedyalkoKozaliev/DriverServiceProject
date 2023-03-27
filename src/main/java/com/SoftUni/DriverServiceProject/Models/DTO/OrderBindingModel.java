package com.SoftUni.DriverServiceProject.Models.DTO;



public class OrderBindingModel {

    private String addressFrom;

    private String addressTo;


    private Integer numberOfPassengers;

    public OrderBindingModel() {
    }

    public String getAddressFrom() {
        return addressFrom;
    }

    public OrderBindingModel setAddressFrom(String addressFrom) {
        this.addressFrom = addressFrom;
        return this;
    }

    public String getAddressTo() {
        return addressTo;
    }

    public OrderBindingModel setAddressTo(String addressTo) {
        this.addressTo = addressTo;
        return this;
    }

    public Integer getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public OrderBindingModel setNumberOfPassengers(Integer numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
        return this;
    }
}

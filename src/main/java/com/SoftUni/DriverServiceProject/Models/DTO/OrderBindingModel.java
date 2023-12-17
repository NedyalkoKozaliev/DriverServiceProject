package com.SoftUni.DriverServiceProject.Models.DTO;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class OrderBindingModel {

    @NotNull
    @Size(min=2)
    private String addressFrom;

    @NotNull
    @Size(min=2)
    private String addressTo;


    @Positive
    private Integer numberOfPassengers;

    private Long client;

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

    public Long getClient() {
        return client;
    }

    public OrderBindingModel setClient(Long client) {
        this.client = client;
        return this;
    }
}

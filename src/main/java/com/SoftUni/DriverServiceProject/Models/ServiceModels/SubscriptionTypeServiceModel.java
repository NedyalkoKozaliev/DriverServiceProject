package com.SoftUni.DriverServiceProject.Models.ServiceModels;

import com.SoftUni.DriverServiceProject.Models.Enums.SubscriptionEnumName;

public class SubscriptionTypeServiceModel {

    private String description;


    private Float priceRate;


    private SubscriptionEnumName name;

    public SubscriptionTypeServiceModel() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPriceRate() {
        return priceRate;
    }

    public void setPriceRate(Float priceRate) {
        this.priceRate = priceRate;
    }

    public SubscriptionEnumName getName() {
        return name;
    }

    public void setName(SubscriptionEnumName name) {
        this.name = name;
    }
}

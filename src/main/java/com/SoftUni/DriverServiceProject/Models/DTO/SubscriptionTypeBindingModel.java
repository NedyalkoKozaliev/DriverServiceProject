package com.SoftUni.DriverServiceProject.Models.DTO;

import com.SoftUni.DriverServiceProject.Models.Enums.SubscriptionEnumName;

public class SubscriptionTypeBindingModel {
    private String description;


    private Float priceRate;


    private SubscriptionEnumName name;

    public SubscriptionTypeBindingModel() {
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

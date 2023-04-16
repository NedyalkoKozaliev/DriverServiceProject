package com.SoftUni.DriverServiceProject.Models.ViewModel;

import com.SoftUni.DriverServiceProject.Models.Enums.SubscriptionEnumName;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class SubscriptionTypeViewModel {

    private Long id;
    private String description;


    private Float priceRate;


    private SubscriptionEnumName name;

    public SubscriptionTypeViewModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

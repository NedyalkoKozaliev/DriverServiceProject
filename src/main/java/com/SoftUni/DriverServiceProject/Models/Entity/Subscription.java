package com.SoftUni.DriverServiceProject.Models.Entity;

import com.SoftUni.DriverServiceProject.Models.Enums.SubscriptionEnumName;
import jakarta.persistence.*;

@Entity
@Table(name="subscriptions")
public class Subscription extends BaseEntity{


    private String description;


    private Float priceRate;


    private SubscriptionEnumName name;

    public Subscription() {
    }

    @Column(columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(nullable = false)
    public Float getPriceRate() {
        return priceRate;
    }

    public void setPriceRate(Float priceRate) {
        this.priceRate = priceRate;
    }

    @Enumerated(EnumType.STRING)
    @Column
    public SubscriptionEnumName getName() {
        return name;
    }

    public void setName(SubscriptionEnumName name) {
        this.name = name;
    }
}

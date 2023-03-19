package com.SoftUni.DriverServiceProject.Models.Entity;

import com.SoftUni.DriverServiceProject.Models.Enums.SubscriptionEnumName;
import jakarta.persistence.*;

@Entity
@Table(name="subscriptions")
public class Subscription extends BaseEntity{

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    private Float priceRate;

    @Enumerated(EnumType.STRING)
    @Column
    private SubscriptionEnumName name;

    public Subscription() {
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

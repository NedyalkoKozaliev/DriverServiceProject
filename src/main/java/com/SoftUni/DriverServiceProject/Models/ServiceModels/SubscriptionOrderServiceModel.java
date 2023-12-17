package com.SoftUni.DriverServiceProject.Models.ServiceModels;

import com.SoftUni.DriverServiceProject.Models.Entity.User;
import com.SoftUni.DriverServiceProject.Models.Enums.SubscriptionEnumName;

public class SubscriptionOrderServiceModel {
    private String addressFrom;

    private String addressTo;

    private SubscriptionEnumName subscription;

    private User client;

    private Float distance;

    public SubscriptionOrderServiceModel() {
    }

    public String getAddressFrom() {
        return addressFrom;
    }

    public void setAddressFrom(String addressFrom) {
        this.addressFrom = addressFrom;
    }

    public String getAddressTo() {
        return addressTo;
    }

    public void setAddressTo(String addressTo) {
        this.addressTo = addressTo;
    }

    public SubscriptionEnumName getSubscription() {
        return subscription;
    }

    public void setSubscription(SubscriptionEnumName subscription) {
        this.subscription = subscription;
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

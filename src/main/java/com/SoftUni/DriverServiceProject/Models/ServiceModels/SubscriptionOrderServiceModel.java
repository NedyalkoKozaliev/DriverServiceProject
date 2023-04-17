package com.SoftUni.DriverServiceProject.Models.ServiceModels;

import com.SoftUni.DriverServiceProject.Models.Enums.SubscriptionEnumName;

public class SubscriptionOrderServiceModel {
    private String addressFrom;

    private String addressTo;

    private SubscriptionEnumName subscription;

    private Long clientId;

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

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}

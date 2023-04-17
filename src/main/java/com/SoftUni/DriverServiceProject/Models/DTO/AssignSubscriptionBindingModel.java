package com.SoftUni.DriverServiceProject.Models.DTO;

public class AssignSubscriptionBindingModel {

private Long subscriptionId;

private Long driverId;

    public AssignSubscriptionBindingModel() {
    }

    public Long getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(Long subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }
}

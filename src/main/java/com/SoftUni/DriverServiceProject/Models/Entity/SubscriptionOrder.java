package com.SoftUni.DriverServiceProject.Models.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="subscriptionOrders")
public class SubscriptionOrder extends BaseEntity{

    @Column
    private String addressFrom;
    @Column
    private String addressTo;

    private Subscription subscription;

    private User client;

    public SubscriptionOrder() {
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

    @ManyToOne
    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    @ManyToOne
    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }
}

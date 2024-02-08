package com.SoftUni.DriverServiceProject.Models.Entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="orders")
public class Order extends BaseEntity{

    @Column(nullable = false)
    private String addressFrom;
    @Column(nullable = false)
    private String addressTo;

    @Column(nullable = false)
    private Integer numberOfPassengers;


    private BigDecimal price;

    private User client;
    private boolean approved;

    @Column(nullable = false)
    private Float distance;

    public Order() {
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

    public Integer getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(Integer numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }



    @ManyToOne
    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    @Column(columnDefinition="Decimal(10,2) default '0.00'")
    public BigDecimal getPrice() {
        return price;
    }

    public Order setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }
}

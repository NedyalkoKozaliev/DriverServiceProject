package com.SoftUni.DriverServiceProject.Models.Entity;

import jakarta.persistence.*;



@Entity
@Table(name="garages")
public class Garage extends BaseEntity{

    @Column
    private String address;



    public Garage() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}

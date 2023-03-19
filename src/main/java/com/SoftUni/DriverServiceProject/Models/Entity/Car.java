package com.SoftUni.DriverServiceProject.Models.Entity;

import com.SoftUni.DriverServiceProject.Models.Enums.CarTypeEnum;
import jakarta.persistence.*;

@Entity
@Table(name="cars")
public class Car extends BaseEntity{

    private CarTypeEnum type;
    private Integer kms;

    private String brand;

    private String model;

    private Garage garage;





    @Enumerated(EnumType.STRING)
    @Column
    public CarTypeEnum getType() {
        return type;
    }

    public void setType(CarTypeEnum type) {
        this.type = type;
    }
    @Column
    public Integer getKms() {
        return kms;
    }

    public void setKms(Integer kms) {
        this.kms = kms;
    }
    @Column
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
    @Column
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @ManyToOne
    public Garage getGarage() {
        return garage;
    }

    public void setGarage(Garage garage) {
        this.garage = garage;
    }
}



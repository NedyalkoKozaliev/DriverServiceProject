package com.SoftUni.DriverServiceProject.Models.ViewModel;

import com.SoftUni.DriverServiceProject.Models.Entity.Garage;
import com.SoftUni.DriverServiceProject.Models.Enums.CarTypeEnum;

public class CarViewModel {

    private Long id;
    private CarTypeEnum type;
    private Integer kms;

    private String brand;

    private String model;

    private Garage garage;

    private String registration;

    public CarViewModel() {
    }

    public CarTypeEnum getType() {
        return type;
    }

    public void setType(CarTypeEnum type) {
        this.type = type;
    }

    public Integer getKms() {
        return kms;
    }

    public void setKms(Integer kms) {
        this.kms = kms;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Garage getGarage() {
        return garage;
    }

    public void setGarage(Garage garage) {
        this.garage = garage;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

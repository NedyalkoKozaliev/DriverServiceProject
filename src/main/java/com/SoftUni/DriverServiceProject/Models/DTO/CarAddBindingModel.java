package com.SoftUni.DriverServiceProject.Models.DTO;

import com.SoftUni.DriverServiceProject.Models.Enums.CarTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class CarAddBindingModel {

        @NotEmpty(message = "Field could not be empty!")
        @Size(min=3, max=20, message = "Registration length must be between 6 and 11 characters.")
        private String registration;

        @NotBlank(message="Field could not be empty!")
        private CarTypeEnum type;

        @NotBlank(message="Field could not be empty!")
        private Integer kms;

        @NotEmpty(message = "Brand should be provided!")
        @Size(min=3, max=20, message = "Brand length must be between 2 and 20 characters.")
        private String brand;

        @NotEmpty(message = "Model should be provided!")
        @Size(min=3, max=20, message = "Model length must be between 2 and 20 characters.")
        private String model;

        @NotBlank
        private String garage; // name of the garage or id -->find it in repo and set it with the service

        public CarAddBindingModel() {
        }

        public String getRegistration() {
                return registration;
        }

        public void setRegistration(String registration) {
                this.registration = registration;
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

        public String getGarage() {
                return garage;
        }

        public void setGarage(String garage) {
                this.garage = garage;
        }
}


package com.SoftUni.DriverServiceProject.Models.DTO;

import com.SoftUni.DriverServiceProject.Models.Enums.CarTypeEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CarAddBindingModel {

        @NotEmpty(message = "Field could not be empty!")
        @Size(min=3, max=20, message = "Registration length must be between 6 and 11 characters.")
        private String registration;

        @NotNull(message="Field could not be empty!")
        private CarTypeEnum type;

        @NotNull(message="Field could not be empty!")
        private Integer kms;

        @NotEmpty(message = "Brand should be provided!")
        @Size(min=3, max=20, message = "Brand length must be between 2 and 20 characters.")
        private String brand;

        @NotEmpty(message = "Model should be provided!")
        @Size(min=3, max=20, message = "Model length must be between 2 and 20 characters.")
        private String model;

        @NotNull
        private String address; // name of the garage or id -->find it in repo and set it with the service

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

        public String getAddress() {
                return address;
        }

        public void setAddress(String address) {
                this.address = address;
        }
}


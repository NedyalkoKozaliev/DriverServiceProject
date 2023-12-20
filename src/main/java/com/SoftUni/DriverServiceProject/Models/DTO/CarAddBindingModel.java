package com.SoftUni.DriverServiceProject.Models.DTO;

import com.SoftUni.DriverServiceProject.Models.Enums.CarTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CarAddBindingModel {

        @NotEmpty
        @Size(min=3, max=20)
        private String registration;

        @NotNull
        private CarTypeEnum type;

        @NotNull
        private Integer kms;

        @NotEmpty
        @Size(min=3, max=20)
        private String brand;

        @NotEmpty
        @Size(min=3, max=20)
        private String model;

        @NotNull
        private String address;

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


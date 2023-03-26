package com.SoftUni.DriverServiceProject.Models.DTO;

public class DistanceDto {

    private String text;

    private Float value;

    public DistanceDto() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }
}

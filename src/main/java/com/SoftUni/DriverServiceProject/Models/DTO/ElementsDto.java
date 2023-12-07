package com.SoftUni.DriverServiceProject.Models.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ElementsDto {

    private DistanceDto  distance;
    @JsonProperty("duration")
    private DurationDto time;

    private String status;

    public ElementsDto() {
    }

    public DistanceDto getDistance() {
        return distance;
    }

    public void setDistance(DistanceDto distance) {
        this.distance = distance;
    }

    public DurationDto getTime() {
        return time;
    }

    public void setTime(DurationDto time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

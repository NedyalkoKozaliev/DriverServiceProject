package com.SoftUni.DriverServiceProject.Models.DTO;

public class ElementsDto {

    private DistanceDto  distance;

    private DurationDto time;

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
}

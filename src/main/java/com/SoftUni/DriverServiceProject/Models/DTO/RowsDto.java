package com.SoftUni.DriverServiceProject.Models.DTO;

public class RowsDto {

    private ElementsDto elemnts;

    private String status;

    public RowsDto() {
    }

    public ElementsDto getElemnts() {
        return elemnts;
    }

    public void setElemnts(ElementsDto elemnts) {
        this.elemnts = elemnts;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

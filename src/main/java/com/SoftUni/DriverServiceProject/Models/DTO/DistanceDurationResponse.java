package com.SoftUni.DriverServiceProject.Models.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DistanceDurationResponse {
    private String[] destination_addresses;


    private String[] origin_addresses;

    @JsonProperty("rows")
    private RowsDto[] rows;

    public DistanceDurationResponse() {
    }

    public String[] getDestination_addresses() {
        return destination_addresses;
    }

    public void setDestination_addresses(String[] destination_addresses) {
        this.destination_addresses = destination_addresses;
    }

    public String[] getOrigin_addresses() {
        return origin_addresses;
    }

    public void setOrigin_addresses(String[] origin_addresses) {
        this.origin_addresses = origin_addresses;
    }

    public RowsDto[] getRows() {
        return rows;
    }

    public void setRows(RowsDto[] rows) {
        this.rows = rows;
    }
}




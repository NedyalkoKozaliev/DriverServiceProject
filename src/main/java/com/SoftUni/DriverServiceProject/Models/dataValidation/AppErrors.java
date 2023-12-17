package com.SoftUni.DriverServiceProject.Models.dataValidation;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class AppErrors {
    private final HttpStatus status;
    private List<String> fieldWithErrors = new ArrayList<>();

    public AppErrors(HttpStatus status) {
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void addFieldWithError(String error) {
        fieldWithErrors.add(error);
    }

    public List<String> getFieldWithErrors() {
        return fieldWithErrors;
    }

    public AppErrors setFieldWithErrors(List<String> fieldWithErrors) {
        this.fieldWithErrors = fieldWithErrors;
        return this;
    }
}

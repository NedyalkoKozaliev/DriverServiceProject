package com.SoftUni.DriverServiceProject.Models.dataValidation;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class AppErorrs {
    private final HttpStatus status;
    private List<String> fieldWithErrors = new ArrayList<>();

    public AppErorrs(HttpStatus status) {
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

    public AppErorrs setFieldWithErrors(List<String> fieldWithErrors) {
        this.fieldWithErrors = fieldWithErrors;
        return this;
    }
}

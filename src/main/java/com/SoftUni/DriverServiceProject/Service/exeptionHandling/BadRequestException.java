package com.SoftUni.DriverServiceProject.Service.exeptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST , reason = "bad request send")
public class BadRequestException extends RuntimeException{

        public BadRequestException(String message) {
            super(message);
        }
    }


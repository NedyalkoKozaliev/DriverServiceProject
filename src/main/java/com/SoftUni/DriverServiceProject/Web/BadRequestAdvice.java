package com.SoftUni.DriverServiceProject.Web;

import com.SoftUni.DriverServiceProject.Service.exeptionHandling.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class BadRequestAdvice {


    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public ModelAndView onBadRequest(BadRequestException bre) {
        ModelAndView modelAndView = new ModelAndView("BadReqiest");

        modelAndView.addObject("message", bre.getMessage());

        return modelAndView;
    }
}

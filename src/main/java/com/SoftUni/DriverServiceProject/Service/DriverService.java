package com.SoftUni.DriverServiceProject.Service;

import com.SoftUni.DriverServiceProject.Models.Entity.Driver;

import java.util.Optional;

public interface DriverService {

    Optional<Driver> getDriver(Long id);

    Driver findDriverById(Long id);

    void finishOrder(Long orderId);

}

package com.SoftUni.DriverServiceProject.Service;

import com.SoftUni.DriverServiceProject.Models.Entity.Driver;
import com.SoftUni.DriverServiceProject.Models.ViewModel.OrderViewModel;

import java.util.List;
import java.util.Optional;

public interface DriverService {

    Optional<Driver> getDriver(Long id);

    Driver findDriverById(Long id);

    void finishOrder(Long id);


    List<Driver> getAllDrivers();

    OrderViewModel assignOrder(Long driverId, Long orderId);


    List<Driver> findAll();

    void assignSubscription(Long id, Long subscriptionId);

    Driver findByEmail(String email);

}

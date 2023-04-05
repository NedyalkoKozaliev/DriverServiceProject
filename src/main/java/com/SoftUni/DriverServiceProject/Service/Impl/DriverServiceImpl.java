package com.SoftUni.DriverServiceProject.Service.Impl;

import com.SoftUni.DriverServiceProject.Models.Entity.Driver;
import com.SoftUni.DriverServiceProject.Models.Entity.Order;
import com.SoftUni.DriverServiceProject.Repository.DriverRepository;
import com.SoftUni.DriverServiceProject.Service.DriverService;
import com.SoftUni.DriverServiceProject.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {
    private final DriverRepository driverRepository;
    private final OrderService orderService;

    @Autowired
    public DriverServiceImpl(DriverRepository driverRepository, OrderService orderService) {
        this.driverRepository = driverRepository;
        this.orderService = orderService;
    }


    @Override
    public Optional<Driver> getDriver(Long id) {
        return null;
    }

    @Override
    public Driver findDriverById(Long id) {
        return  driverRepository.findDriverById(id);
    }

    @Override
    public void finishOrder(Long id) {
        Order order=driverRepository.findDriverById(id).getCurrentTask();
        driverRepository.findDriverById(id).setCurrentTask(null);
        driverRepository.findDriverById(id).getOrderTasks().add(order);
    }


}

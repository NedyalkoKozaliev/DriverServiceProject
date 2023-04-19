package com.SoftUni.DriverServiceProject.Service.Impl;

import com.SoftUni.DriverServiceProject.Models.Entity.Driver;
import com.SoftUni.DriverServiceProject.Models.Entity.Order;
import com.SoftUni.DriverServiceProject.Models.Entity.SubscriptionOrder;
import com.SoftUni.DriverServiceProject.Models.ViewModel.OrderViewModel;
import com.SoftUni.DriverServiceProject.Repository.DriverRepository;
import com.SoftUni.DriverServiceProject.Service.DriverService;
import com.SoftUni.DriverServiceProject.Service.OrderService;
import com.SoftUni.DriverServiceProject.Service.SubscriptionOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {
    private final DriverRepository driverRepository;
    private final OrderService orderService;

    private final ModelMapper modelMapper;

    private final SubscriptionOrderService subscriptionOrderService;

    @Autowired
    public DriverServiceImpl(DriverRepository driverRepository, OrderService orderService, ModelMapper modelMapper, SubscriptionOrderService subscriptionOrderService) {
        this.driverRepository = driverRepository;
        this.orderService = orderService;
        this.modelMapper = modelMapper;

        this.subscriptionOrderService = subscriptionOrderService;
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
        Driver driver= driverRepository.findDriverById(id);
       driver.setCurrentTask(null);
       driver.setAvailable(true);
        driver.getOrderTasks().add(order);
        driverRepository.save(driver);
    }

    @Override
    public List<Driver> getAllDrivers() {
        return null;
    }

    @Override
    public OrderViewModel assignOrder(Long driverId, Long orderId) {

        Order order=orderService.findOrderById(orderId);
        Driver driver=findDriverById(driverId);
        driver.setCurrentTask(order);
        driver.setAvailable(false);
        driverRepository.save(driver);
        OrderViewModel orderViewModel=modelMapper.map(order,OrderViewModel.class);

        return orderViewModel;
    }

    @Override
    public List<Driver> findAll() {
        return driverRepository.findAll();
    }

    @Override
    public void assignSubscription(Long id, Long subscriptionId) {
        Driver driver=findDriverById(id);
       SubscriptionOrder subscriptionOrder=subscriptionOrderService.findSubscriptionOrderById(subscriptionId);
        driver.getSubscriptionTasks().add(subscriptionOrder);
        driverRepository.save(driver);
    }

    @Override
    public Driver findByEmail(String email) {
        return driverRepository.findDriverByEmail(email);
    }


}

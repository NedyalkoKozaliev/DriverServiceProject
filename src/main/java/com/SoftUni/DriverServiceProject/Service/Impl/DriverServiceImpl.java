package com.SoftUni.DriverServiceProject.Service.Impl;

import com.SoftUni.DriverServiceProject.Models.Entity.Driver;
import com.SoftUni.DriverServiceProject.Repository.DriverRepository;
import com.SoftUni.DriverServiceProject.Service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {
    private final DriverRepository driverRepository;

    @Autowired
    public DriverServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }


    @Override
    public Optional<Driver> getDriver(Long id) {
        return driverRepository.findDriverById(id);
    }
}

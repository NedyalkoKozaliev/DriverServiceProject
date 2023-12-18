package com.SoftUni.DriverServiceProject.Service.Impl;

import com.SoftUni.DriverServiceProject.Models.Entity.Car;
import com.SoftUni.DriverServiceProject.Models.ServiceModels.CarServiceModel;
import com.SoftUni.DriverServiceProject.Models.ViewModel.CarViewModel;
import com.SoftUni.DriverServiceProject.Repository.CarRepository;
import com.SoftUni.DriverServiceProject.Service.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private final ModelMapper modelMapper;
    private final CarRepository carRepository;

    public CarServiceImpl(ModelMapper modelMapper, CarRepository carRepository) {
        this.modelMapper = modelMapper;
        this.carRepository = carRepository;
    }


    @Override
    public CarViewModel createCar(CarServiceModel carServiceModel) {

        Car car=modelMapper.map(carServiceModel,Car.class);
        carRepository.save(car);

        return modelMapper.map(car,CarViewModel.class);
    }

    @Override
    public Car findCar(String registration) {
        return carRepository.findByRegistration(registration).orElse(null);
    }

    @Override
    public List<Car> findAllCars() {
        return carRepository.findAll();
    }
}

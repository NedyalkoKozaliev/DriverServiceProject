package com.SoftUni.DriverServiceProject.Service;

import com.SoftUni.DriverServiceProject.Models.Entity.Car;
import com.SoftUni.DriverServiceProject.Models.ServiceModels.CarServiceModel;
import com.SoftUni.DriverServiceProject.Models.ViewModel.CarViewModel;

import java.util.List;

public interface CarService {

    CarViewModel createCar(CarServiceModel carServiceModel);

    Car findCar(String registration);

    List<Car> findAllCars();

}

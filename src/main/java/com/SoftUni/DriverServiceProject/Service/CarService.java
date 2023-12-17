package com.SoftUni.DriverServiceProject.Service;

import com.SoftUni.DriverServiceProject.Models.Entity.Car;
import com.SoftUni.DriverServiceProject.Models.ServiceModels.CarServiceModel;
import com.SoftUni.DriverServiceProject.Models.ViewModel.CarViewModel;

public interface CarService {

    CarViewModel createCar(CarServiceModel carServiceModel);

    Car findCar(String registration);

}

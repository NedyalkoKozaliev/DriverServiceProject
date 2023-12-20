package com.SoftUni.DriverServiceProject.Service;

import com.SoftUni.DriverServiceProject.Models.Entity.Garage;
import com.SoftUni.DriverServiceProject.Models.ServiceModels.GarageServiceModel;
import com.SoftUni.DriverServiceProject.Models.ViewModel.GarageViewModel;

import java.util.List;

public interface GarageService {


    GarageViewModel createGarage(GarageServiceModel garageServiceModel);

    Garage findGarage(String address);

    List<Garage> findAll();

}

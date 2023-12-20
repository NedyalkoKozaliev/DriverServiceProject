package com.SoftUni.DriverServiceProject.Service.Impl;

import com.SoftUni.DriverServiceProject.Models.Entity.Garage;
import com.SoftUni.DriverServiceProject.Models.ServiceModels.GarageServiceModel;
import com.SoftUni.DriverServiceProject.Models.ViewModel.GarageViewModel;
import com.SoftUni.DriverServiceProject.Repository.GarageRepository;
import com.SoftUni.DriverServiceProject.Service.GarageService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GarageServiceImpl implements GarageService {

    private final ModelMapper modelMapper;
    private final GarageRepository repository;

    public GarageServiceImpl(ModelMapper modelMapper, GarageRepository repository) {
        this.modelMapper = modelMapper;
        this.repository = repository;
    }

    @Override
    public GarageViewModel createGarage(GarageServiceModel garageServiceModel) {

        Garage garage=modelMapper.map(garageServiceModel,Garage.class);
        repository.save(garage);
        return modelMapper.map(garage,GarageViewModel.class);
    }

    @Override
    public Garage findGarage(String address) {
        return repository.findByAddress(address).orElseThrow(null);
    }

 @Override
    public List<Garage> findAll(){
        return repository.findAll();
    }

}

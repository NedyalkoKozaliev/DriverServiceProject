package com.SoftUni.DriverServiceProject.Web;

import com.SoftUni.DriverServiceProject.Models.DTO.CarAddBindingModel;
import com.SoftUni.DriverServiceProject.Models.DTO.DriverAddBindingModel;
import com.SoftUni.DriverServiceProject.Models.DTO.GarageAddBindingModel;
import com.SoftUni.DriverServiceProject.Models.DTO.SubscriptionTypeBindingModel;
import com.SoftUni.DriverServiceProject.Models.ServiceModels.CarServiceModel;
import com.SoftUni.DriverServiceProject.Models.ServiceModels.DriverServiceModel;
import com.SoftUni.DriverServiceProject.Models.ServiceModels.GarageServiceModel;
import com.SoftUni.DriverServiceProject.Models.ServiceModels.SubscriptionTypeServiceModel;
import com.SoftUni.DriverServiceProject.Models.ViewModel.CarViewModel;
import com.SoftUni.DriverServiceProject.Models.ViewModel.DriverViewModel;
import com.SoftUni.DriverServiceProject.Models.ViewModel.GarageViewModel;
import com.SoftUni.DriverServiceProject.Models.ViewModel.SubscriptionTypeViewModel;
import com.SoftUni.DriverServiceProject.Models.dataValidation.AppErrors;
import com.SoftUni.DriverServiceProject.Service.*;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@CrossOrigin("*")
@RestController
public class AdminRestController {

        private final ModelMapper modelMapper;
        private  final DriverService driverService;
        private final OrderService orderService;

        private final CarService carService;

        private final GarageService garageService;

        private final SubscriptionTypeService subscriptionTypeService;

    public AdminRestController(ModelMapper modelMapper, DriverService driverService, OrderService orderService, CarService carService, GarageService garageService, SubscriptionTypeService subscriptionTypeService) {
        this.modelMapper = modelMapper;
        this.driverService = driverService;
        this.orderService = orderService;
        this.carService = carService;
        this.garageService = garageService;
        this.subscriptionTypeService = subscriptionTypeService;
    }

    @RequestMapping(value = "/api/admins/{id}/createDriver",
                produces = "application/json", method = {RequestMethod.POST})
        public ResponseEntity<DriverViewModel> NewDriver(
                @PathVariable Long id,
                @AuthenticationPrincipal UserDetails principal,
                @RequestBody @Valid DriverAddBindingModel driverAddBindingModel
        ) {
            DriverServiceModel driverServiceModel =

                    modelMapper.map(driverAddBindingModel, DriverServiceModel.class);

                driverServiceModel.setCar(carService.findCar(driverAddBindingModel.getRegistration()));

            DriverViewModel DriverView =
                    driverService.createDriver(driverServiceModel);


            URI locationOfNewViewDriver =
                    URI.create(String.format("/api/drivers/%s", DriverView.getId()));

            return ResponseEntity.
                    created(locationOfNewViewDriver).
                    body(DriverView);
        }





        @RequestMapping(value = "/api/admins/{id}/createCar",
                produces = "application/json", method = {RequestMethod.POST})
        public ResponseEntity<CarViewModel> NewCar(
                @PathVariable Long id,
                @AuthenticationPrincipal UserDetails principal,
                @RequestBody @Valid CarAddBindingModel carAddBindingModel
        ) {
            CarServiceModel carServiceModel =
                    modelMapper.map(carAddBindingModel, CarServiceModel.class);

                carServiceModel.setGarage(garageService.findGarage(carAddBindingModel.getAddress()));

            CarViewModel CarView =
                    carService.createCar(carServiceModel);


            URI locationOfNewViewCar =
                    URI.create(String.format("/api/admins/cars/%s", CarView.getId()));

            return ResponseEntity.
                    created(locationOfNewViewCar).
                    body(CarView);


        }


        @RequestMapping(value = "/api/admins/{id}/createGarage",
                produces = "application/json", method = {RequestMethod.POST})
        public ResponseEntity<GarageViewModel> NewGarage(
                @PathVariable Long id,
                @AuthenticationPrincipal UserDetails principal,
                @RequestBody @Valid GarageAddBindingModel garageAddBindingModel
        ) {

            GarageServiceModel garageServiceModel =

                    modelMapper.map(garageAddBindingModel, GarageServiceModel.class);


            GarageViewModel GarageView =
                    garageService.createGarage(garageServiceModel);


            URI locationOfNewViewGarage =
                    URI.create(String.format("/api/admins/garages/%s", GarageView.getId()));

            return ResponseEntity.
                    created(locationOfNewViewGarage).
                    body(GarageView);




        }

        @RequestMapping(value = "/api/admins/{id}/changeSubscription",
                produces = "application/json", method = {RequestMethod.PUT})
        public ResponseEntity<SubscriptionTypeViewModel> ChangeSubscription(
                @PathVariable Long id,
                @AuthenticationPrincipal UserDetails principal,
                @RequestBody @Valid SubscriptionTypeBindingModel subscriptionTypeBindingModel
        ) {


            SubscriptionTypeServiceModel subscriptionTypeServiceModel =
                    modelMapper.map(subscriptionTypeBindingModel, SubscriptionTypeServiceModel.class);

            SubscriptionTypeViewModel subscriptionTypeViewModel=
                    subscriptionTypeService.changeType(subscriptionTypeServiceModel);

            URI locationOfNewViewSubscription =
                    URI.create(String.format("/api/admins/subscriptions/%s", subscriptionTypeViewModel.getId()));

            return ResponseEntity.
                    created(locationOfNewViewSubscription).
                    body(subscriptionTypeViewModel);


        }



        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<AppErrors> onValidationFailure(MethodArgumentNotValidException exc) {
            AppErrors apiError = new AppErrors(HttpStatus.BAD_REQUEST);
            exc.getFieldErrors().forEach(fe ->
                    apiError.addFieldWithError(fe.getField()));

            return ResponseEntity.badRequest().body(apiError);
        }
    }









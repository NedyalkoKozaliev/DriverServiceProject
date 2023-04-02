package com.SoftUni.DriverServiceProject.Web;


import com.SoftUni.DriverServiceProject.Models.DTO.OrderBindingModel;
import com.SoftUni.DriverServiceProject.Models.Entity.Driver;
import com.SoftUni.DriverServiceProject.Models.ServiceModels.OrderServiceModel;
import com.SoftUni.DriverServiceProject.Models.ViewModel.OrderViewModel;
import com.SoftUni.DriverServiceProject.Repository.DriverRepository;
import com.SoftUni.DriverServiceProject.Service.DriverService;
import com.SoftUni.DriverServiceProject.Service.OrderService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@CrossOrigin("*")
@RestController
public class DriverRestController {

    private final ModelMapper modelMapper;
    private  final DriverService driverService;

    public DriverRestController(ModelMapper modelMapper, DriverService driverService) {
        this.modelMapper = modelMapper;
        this.driverService = driverService;
    }

    @PutMapping("/api/drivers/{id}/currentOrder") //----!!!!!!!!!!!--------> id на края трябва да го сложа , ако не сработи
    public ResponseEntity<OrderServiceModel> AssignOrder(
            @AuthenticationPrincipal UserDetails principal,
            @PathVariable Long id,
            @RequestBody @Valid OrderBindingModel orderBindingModel,
              OrderService orderService){

        OrderServiceModel orderServiceModel=modelMapper.map(orderBindingModel, OrderServiceModel.class);

        OrderViewModel OrderView =
                orderService.createOrder(orderServiceModel); // --->ъпдейт драйвър трябва да бъде не променям поръчката/може да и сложа цената тук/

        URI locationOfNewOrder =
                URI.create(String.format("/api/drivers/%s/currentOrder",id));

        Driver driver=driverService.findDriverById(id); //--------------> ъпдейт драйвър в сървиса
        driver.setCurrentTask(orderService.getOrder(orderServiceModel.getId()));

        //-------> ъпдейт драйвър в сървиса
        return ResponseEntity
                .created(locationOfNewOrder)
                .body(orderServiceModel);}

    @PutMapping("/api/drivers/{id}/ordersList")
    public ResponseEntity<OrderServiceModel> FinishOrder(
            @AuthenticationPrincipal UserDetails principal,
            @PathVariable Long id,
            @RequestBody @Valid OrderServiceModel orderServiceModel){

        Long orderId=orderServiceModel.getId();

        driverService.finishOrder(orderId); //---->move order to the list


        return ResponseEntity.
                noContent().
                build();

    }

}


package com.SoftUni.DriverServiceProject.Web;

import com.SoftUni.DriverServiceProject.Models.DTO.OrderBindingModel;
import com.SoftUni.DriverServiceProject.Models.Entity.Driver;
import com.SoftUni.DriverServiceProject.Models.ServiceModels.OrderServiceModel;
import com.SoftUni.DriverServiceProject.Models.ViewModel.OrderViewModel;
import com.SoftUni.DriverServiceProject.Repository.DriverRepository;
import com.SoftUni.DriverServiceProject.Service.OrderService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/drivers")
public class DriverController {



    @PutMapping("/{id}")
    public ResponseEntity<OrderViewModel> AssignOrder(
            @AuthenticationPrincipal UserDetails principal,
            @PathVariable Long id,
            @RequestBody @Valid OrderBindingModel orderBindingModel,
            DriverRepository driverRepository, ModelMapper modelMapper, OrderService orderService){

        OrderServiceModel orderServiceModel=modelMapper.map(orderBindingModel, OrderServiceModel.class);

        OrderViewModel OrderView =
                orderService.createOrder(orderServiceModel);
        URI locationOfNewOrder =
                URI.create(String.format("/drivers/%s/currentOrder",id));

        Optional<Driver> driver=driverRepository.findById(id);

        driver.ifPresent(driver1 ->{ driver1.setCurrentTask(orderService.getOrder(orderServiceModel.getId()));});
           return ResponseEntity
                   .created(locationOfNewOrder)
                   .body(OrderView);}

    @PutMapping("/{id}/currentOrder")
    public ResponseEntity<OrderViewModel> FinishOrder(
            @AuthenticationPrincipal UserDetails principal,
            @PathVariable Long id,
            @RequestBody @Valid OrderBindingModel orderBindingModel){
        return null;

    }

}







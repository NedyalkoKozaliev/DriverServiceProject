package com.SoftUni.DriverServiceProject.Web;


import com.SoftUni.DriverServiceProject.Models.Entity.Driver;
import com.SoftUni.DriverServiceProject.Models.ServiceModels.OrderServiceModel;
import com.SoftUni.DriverServiceProject.Models.ViewModel.OrderViewModel;
import com.SoftUni.DriverServiceProject.Service.DriverService;
import com.SoftUni.DriverServiceProject.Service.OrderService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@CrossOrigin("*")
@RestController
public class DriverRestController {

    private final ModelMapper modelMapper;
    private  final DriverService driverService;
    private final OrderService orderService;

    public DriverRestController(ModelMapper modelMapper, DriverService driverService, OrderService orderService) {
        this.modelMapper = modelMapper;
        this.driverService = driverService;
        this.orderService = orderService;
    }

    @PutMapping("/api/drivers/{id}/currentOrder") //----!!!!!!!!!!!--------> id на края трябва да го сложа , ако не сработи
    public ResponseEntity<OrderViewModel> AssignOrder(
            @AuthenticationPrincipal UserDetails principal,
            @PathVariable Long id,
             @Valid OrderViewModel orderViewModel){


        driverService.findDriverById(id).setCurrentTask(orderService.findOrderById(orderViewModel.getId()));


        URI locationOfNewOrder =
                URI.create(String.format("/api/drivers/%s/currentOrder",id));



        return ResponseEntity
                .created(locationOfNewOrder)
                .body(orderViewModel);}



    @GetMapping("/api/drivers/{id}/currentOrder")
    public ResponseEntity<OrderViewModel> getCurrentTask(
            @PathVariable Long id,
            UserDetails principal
    ) {
        Optional<OrderViewModel> orderViewModel=orderService.getOrderById(driverService.findDriverById(id).getCurrentTask().getId());
        return orderViewModel.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }


//    @PutMapping("/api/drivers/{id}/ordersList")
//    public ResponseEntity<OrderServiceModel> FinishOrder(
//            @AuthenticationPrincipal UserDetails principal,
//            @PathVariable Long id,
//            @RequestBody @Valid OrderServiceModel orderServiceModel){
//
//        Long orderId=orderServiceModel.;
//
//        driverService.finishOrder(orderId); //---->move order to the list
//
//
//        return ResponseEntity.
//                noContent().
//                build();
//
//    }

}


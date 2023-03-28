package com.SoftUni.DriverServiceProject.Web;

import com.SoftUni.DriverServiceProject.Models.DTO.OrderBindingModel;
import com.SoftUni.DriverServiceProject.Models.Entity.User;
import com.SoftUni.DriverServiceProject.Models.ServiceModels.OrderServiceModel;
import com.SoftUni.DriverServiceProject.Models.ViewModel.OrderViewModel;
import com.SoftUni.DriverServiceProject.Service.OrderService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/clients")
public class ClientsController {

    @PostMapping("/{id}")
    public ResponseEntity<OrderViewModel> OrderIn(
            @AuthenticationPrincipal UserDetails principal,
            @PathVariable Long clientId,
            @RequestBody @Valid OrderBindingModel OrderBindingModel, ModelMapper modelMapper, OrderService
            orderService) {

        //!!find order by received info and show data as response binding model should be different

        OrderServiceModel orderServiceModel =
                modelMapper.map(OrderBindingModel, OrderServiceModel.class);
        orderServiceModel.setClient((User) principal);


        OrderViewModel OrderView =
                orderService.createOrder(orderServiceModel);

        URI locationOfNewOrder =
                URI.create(String.format("/api/orders/%s",OrderView.getId()));

        return ResponseEntity.
                created(locationOfNewOrder).
                body(OrderView);
    }

//    @GetMapping("/user/client/{id}/my_order") //not from here instead from orders
//    public ResponseEntity<OrderViewModel> getApprovedOrder(
//            @PathVariable("id") Long id,
//            Principal principal
//    ) {
//        return null;//if order is in the list of driver

//        Optional<OrderViewModel> thisOrder=orderService.getOrderById(id);
//
//        return thisOrder.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());

    }


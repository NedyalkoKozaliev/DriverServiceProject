package com.SoftUni.DriverServiceProject.Web;

import com.SoftUni.DriverServiceProject.Models.DTO.OrderBindingModel;
import com.SoftUni.DriverServiceProject.Models.Entity.Order;
import com.SoftUni.DriverServiceProject.Models.Entity.User;
import com.SoftUni.DriverServiceProject.Models.ServiceModels.OrderServiceModel;
import com.SoftUni.DriverServiceProject.Models.ViewModel.OrderViewModel;
import com.SoftUni.DriverServiceProject.Service.OrderService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
public class OrderRestController {

    private final ModelMapper modelMapper;
    private final OrderService orderService;



    public OrderRestController(ModelMapper modelMapper, OrderService orderService) {
        this.modelMapper = modelMapper;
        this.orderService = orderService;
    }

    @RequestMapping(value="/api/orders",
            produces="application/json",method = {RequestMethod.POST})
    public ResponseEntity<OrderViewModel> OrderIn(
            @AuthenticationPrincipal UserDetails principal,
             @Valid OrderBindingModel OrderBindingModel
    ) {

        OrderServiceModel orderServiceModel =
                modelMapper.map(OrderBindingModel, OrderServiceModel.class);
       // orderServiceModel.setClient((User) principal);



        OrderViewModel OrderView =
                orderService.createOrder(orderServiceModel);


        URI locationOfNewViewOrder =
                URI.create(String.format("/api/orders/%s",OrderView.getId()));

        return ResponseEntity.
                created(locationOfNewViewOrder).
                body(OrderView);
    }

    @RequestMapping(value="/api/orders",
            produces="application/json",method = {RequestMethod.GET})
    public ResponseEntity<List<OrderViewModel>> getOrders()
    {
        return ResponseEntity.ok(
                orderService.getAllOrders());
    }


    @GetMapping("api/orders/{id}")
    public ResponseEntity<OrderViewModel> getOrder(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails principal
    ) {

       Optional<OrderViewModel> orderViewModel=orderService.getOrderById(id);
        return orderViewModel.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

}

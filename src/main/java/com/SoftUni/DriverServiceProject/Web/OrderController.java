package com.SoftUni.DriverServiceProject.Web;

import com.SoftUni.DriverServiceProject.Models.DTO.OrderBindingModel;
import com.SoftUni.DriverServiceProject.Models.Entity.User;
import com.SoftUni.DriverServiceProject.Models.ServiceModels.OrderServiceModel;
import com.SoftUni.DriverServiceProject.Models.ViewModel.OrderViewModel;
import com.SoftUni.DriverServiceProject.Service.Impl.OrderServiceImpl;
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
@RequestMapping("/orders")
public class OrderController {
///users/clients/orders
    private final ModelMapper modelMapper;
    private final OrderService orderService;



    public OrderController(ModelMapper modelMapper, OrderService orderService) {
        this.modelMapper = modelMapper;
        this.orderService = orderService;
    }

    //1.create order  ///users/clients/{id}/orders
@PostMapping //need to change it probably has it
public ResponseEntity<OrderViewModel> OrderIn(
        @AuthenticationPrincipal UserDetails principal,
        // @PathVariable Long routeId,
        @RequestBody @Valid OrderBindingModel OrderBindingModel //getting the Json response from js file (handleCommentSubmit()) and map to bindingmodel
) { //By default, the type we annotate with the @RequestBody annotation must correspond
        // to the JSON sent from our client-side controller

    OrderServiceModel orderServiceModel =
            modelMapper.map(OrderBindingModel, OrderServiceModel.class);
    orderServiceModel.setClient((User) principal);
    //set other missings in the form things
    // orderServiceModell.setCreator(principal.getUsername());
    // orderServiceModel.setRouteId(routeId);

    OrderViewModel OrderView =
            orderService.createOrder(orderServiceModel);

    URI locationOfNewOrder =       //define where should it be as url with the id for each (uri gives that id option)
            URI.create(String.format("/orders/%s",OrderView.getId()));

    return ResponseEntity.
            created(locationOfNewOrder).
            body(OrderView);
}
///order/{id}"
//@GetMapping("/users/clients/orders/{id}")
//public ResponseEntity<OrderViewModel> getOrder(
//        @PathVariable ("id") Long id,
//        Principal principal
//) {
//    Optional<OrderViewModel> thisOrder=orderService.getOrderById(id);
//
//    return thisOrder.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
//
//}
//    @PostMapping("users/clients/{id}/orders" ) //need to change it probably has it
//    public ResponseEntity<OrderViewModel> myOrder(
//            @AuthenticationPrincipal UserDetails principal,
//            // @PathVariable Long routeId,
//            @RequestBody @Valid OrderBindingModel OrderBindingModel //getting the Json response from js file (handleCommentSubmit()) and map to bindingmodel
//    ) {
//        //receive post for the approved order return the order
//        return  null;
//    }

//2.collect all orders of a client do something with it

//3.collect all orders in general do something with it

//4.delete order

//5.refuse order

}

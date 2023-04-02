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
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/orders")
public class OrderRestController {

    private final ModelMapper modelMapper;
    private final OrderService orderService;



    public OrderRestController(ModelMapper modelMapper, OrderService orderService) {
        this.modelMapper = modelMapper;
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderViewModel> OrderIn(
            @AuthenticationPrincipal UserDetails principal,
            @RequestBody @Valid OrderBindingModel OrderBindingModel
    ) {

        OrderServiceModel orderServiceModel =
                modelMapper.map(OrderBindingModel, OrderServiceModel.class);
        orderServiceModel.setClient((User) principal);

        OrderViewModel OrderView =
                orderService.createOrder(orderServiceModel);

        URI locationOfNewViewOrder =
                URI.create(String.format("/api/orders/%s",OrderView.getId()));

        return ResponseEntity.
                created(locationOfNewViewOrder).
                body(OrderView);
    }

//@GetMapping("api/orders/{id}")
//public ResponseEntity<OrderViewModel> getOrder(
//        @PathVariable ("id") Long id,
//        UserDetails principal
//) {
//    Optional<OrderViewModel> thisOrder=orderService.getOrderById(id);
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

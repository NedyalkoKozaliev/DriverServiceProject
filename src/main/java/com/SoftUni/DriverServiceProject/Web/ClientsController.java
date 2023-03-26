package com.SoftUni.DriverServiceProject.Web;

import com.SoftUni.DriverServiceProject.Models.ViewModel.OrderViewModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/user/client")
public class ClientsController {

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


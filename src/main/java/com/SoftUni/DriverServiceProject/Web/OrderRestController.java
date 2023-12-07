package com.SoftUni.DriverServiceProject.Web;

import com.SoftUni.DriverServiceProject.Models.DTO.DistanceDurationResponse;
import com.SoftUni.DriverServiceProject.Models.DTO.OrderBindingModel;
import com.SoftUni.DriverServiceProject.Models.Entity.Order;
import com.SoftUni.DriverServiceProject.Models.Entity.User;
import com.SoftUni.DriverServiceProject.Models.ServiceModels.OrderServiceModel;
import com.SoftUni.DriverServiceProject.Models.ViewModel.OrderViewModel;
import com.SoftUni.DriverServiceProject.Models.dataValidation.AppErorrs;
import com.SoftUni.DriverServiceProject.Service.OrderService;
import jakarta.validation.Valid;
import jdk.jfr.ContentType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
public class OrderRestController {

    private final ModelMapper modelMapper;
    private final OrderService orderService;
    private static final Object API_KEY="";


   // @Autowired
    public OrderRestController(ModelMapper modelMapper, OrderService orderService) {
        this.modelMapper = modelMapper;
        this.orderService = orderService;
    }

    @RequestMapping(value = "/api/orders",
            produces = "application/json", method = {RequestMethod.POST})
    public ResponseEntity<OrderViewModel> OrderIn(
            @AuthenticationPrincipal UserDetails principal,
            @RequestBody @Valid OrderBindingModel orderBindingModel
    ) {

        String addressFrom=orderBindingModel.getAddressFrom();
        String addressTo=orderBindingModel.getAddressTo();

//        UriComponents uri = UriComponentsBuilder.newInstance()
//                .scheme("https")
//                .host("maps.googleapis.com")
//                .path("/maps/api/distancematrix")
//                .queryParam("key", API_KEY)
//                .queryParam("origins", addressFrom)
//                .queryParam("destinations", addressTo)
//                .build();
        ResponseEntity<DistanceDurationResponse> response= new RestTemplate().getForEntity("https://maps.googleapis.com/maps/api/distancematrix/json?origins="+addressFrom+"&destinations="+addressTo+"&mode=car&language=en&key="+API_KEY, DistanceDurationResponse.class);


        Float distance= Arrays.stream(Arrays.stream(response.getBody().getRows()).findFirst().get().getElements()).findFirst().get().getDistance().getValue();


        OrderServiceModel orderServiceModel =
               modelMapper.map(orderBindingModel, OrderServiceModel.class);

        orderServiceModel.setDistance(distance/1000);


        OrderViewModel OrderView =
                orderService.createOrder(orderServiceModel);


        URI locationOfNewViewOrder =
                URI.create(String.format("/api/orders/%s", OrderView.getId()));

        return ResponseEntity.
                created(locationOfNewViewOrder).
                body(OrderView);
    }

    @RequestMapping(value = "/api/orders",
            produces = "application/json", method = {RequestMethod.GET})
    public ResponseEntity<List<OrderViewModel>> getOrders() {
        return ResponseEntity.ok(
                orderService.getAllOrders());
    }


    @RequestMapping(value = "/api/orders/{id}",
            produces = "application/json", method = {RequestMethod.GET})
    public ResponseEntity<OrderViewModel> getOrder(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails principal
    ) {

        OrderViewModel orderViewModel = orderService.getOrderById(id);
        return ResponseEntity.ok(orderViewModel);
       // return orderViewModel.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }




    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AppErorrs> onValidationFailure(MethodArgumentNotValidException exc) {
        AppErorrs appErorrs = new AppErorrs(HttpStatus.BAD_REQUEST);
        exc.getFieldErrors().forEach(fe ->
                appErorrs.addFieldWithError(fe.getField()));

        return ResponseEntity.badRequest().body(appErorrs);
    }
}

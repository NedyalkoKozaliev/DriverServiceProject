package com.SoftUni.DriverServiceProject.Web;

import com.SoftUni.DriverServiceProject.Models.DTO.DistanceDurationResponse;
import com.SoftUni.DriverServiceProject.Models.DTO.OrderBindingModel;
import com.SoftUni.DriverServiceProject.Models.ServiceModels.OrderServiceModel;
import com.SoftUni.DriverServiceProject.Models.ViewModel.OrderViewModel;
import com.SoftUni.DriverServiceProject.Models.dataValidation.AppErrors;
import com.SoftUni.DriverServiceProject.Service.ClientService;
import com.SoftUni.DriverServiceProject.Service.OrderService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@CrossOrigin("*")
@RestController
public class OrderRestController {

    private final ModelMapper modelMapper;
    private final OrderService orderService;
    private final ClientService clientService;
    private static final Object API_KEY="";


   // @Autowired
    public OrderRestController(ModelMapper modelMapper, OrderService orderService, ClientService clientService) {
        this.modelMapper = modelMapper;
        this.orderService = orderService;

        this.clientService = clientService;
    }

    @RequestMapping(value = "/api/orders",
            produces = "application/json", method = {RequestMethod.POST})
    public ResponseEntity<OrderViewModel> OrderIn(
            @AuthenticationPrincipal UserDetails principal,
            @RequestBody @Valid OrderBindingModel orderBindingModel
    ) {

        String addressTo=orderBindingModel.getAddressTo();
        String addressFrom=orderBindingModel.getAddressFrom();

       ResponseEntity<DistanceDurationResponse> response= new RestTemplate().getForEntity("https://maps.googleapis.com/maps/api/distancematrix/json?origins="+addressFrom+"&destinations="+addressTo+"&mode=car&language=en&key="+API_KEY, DistanceDurationResponse.class);

//DistanceDurationResponse response1=new RestTemplate().getForObject("http://localhost:8080/api/DistanceAndDuration",DistanceDurationResponse.class,addressFrom,addressTo);
        Float distance= Arrays.stream(Arrays.stream(response.getBody().getRows()).findFirst().get().getElements()).findFirst().get().getDistance().getValue();


        OrderServiceModel orderServiceModel =
               modelMapper.map(orderBindingModel, OrderServiceModel.class);
        orderServiceModel.setClient(clientService.findClientByEmail(principal.getUsername()));

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
    public ResponseEntity<AppErrors> onValidationFailure(MethodArgumentNotValidException exc) {
        AppErrors appErrors = new AppErrors(HttpStatus.BAD_REQUEST);
        exc.getFieldErrors().forEach(fe ->
                appErrors.addFieldWithError(fe.getField()));

        return ResponseEntity.badRequest().body(appErrors);
    }
}

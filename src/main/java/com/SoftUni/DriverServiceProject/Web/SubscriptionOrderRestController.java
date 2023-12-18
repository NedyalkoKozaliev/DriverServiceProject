package com.SoftUni.DriverServiceProject.Web;

import com.SoftUni.DriverServiceProject.Models.DTO.DistanceDurationResponse;
import com.SoftUni.DriverServiceProject.Models.DTO.SubscriptionOrderBindingModel;
import com.SoftUni.DriverServiceProject.Models.ServiceModels.SubscriptionOrderServiceModel;
import com.SoftUni.DriverServiceProject.Models.ViewModel.SubscriptionOrderViewModel;
import com.SoftUni.DriverServiceProject.Models.dataValidation.AppErrors;
import com.SoftUni.DriverServiceProject.Service.ClientService;
import com.SoftUni.DriverServiceProject.Service.SubscriptionOrderService;
import com.SoftUni.DriverServiceProject.Service.SubscriptionTypeService;
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

@CrossOrigin("*")
@RestController
public class SubscriptionOrderRestController {

    private final ModelMapper modelMapper;
    private final SubscriptionOrderService subscriptionOrderService;
    private final SubscriptionTypeService subscriptionTypeService;

    private final ClientService clientService;

    private static final Object API_KEY="AIzaSyBWwHYSB5F8eJXlS6wfypKDvt84lhO9ipg";

    public SubscriptionOrderRestController(ModelMapper modelMapper, SubscriptionOrderService subscriptionOrderService, SubscriptionTypeService subscriptionTypeService, ClientService clientService) {
        this.modelMapper = modelMapper;
        this.subscriptionOrderService = subscriptionOrderService;
        this.subscriptionTypeService = subscriptionTypeService;
        this.clientService = clientService;
    }


    @RequestMapping(value = "/api/subscriptionOrders",
            produces = "application/json", method = {RequestMethod.POST})
    public ResponseEntity<SubscriptionOrderViewModel> MakeSubscriptionOrder(
            @AuthenticationPrincipal UserDetails principal,
            @RequestBody @Valid SubscriptionOrderBindingModel subscriptionOrderBindingModel
    ) {

        SubscriptionOrderServiceModel subscriptionOrderServiceModel=modelMapper.map(subscriptionOrderBindingModel,
                SubscriptionOrderServiceModel.class);
        subscriptionOrderServiceModel.setClient(clientService.findClientByEmail(principal.getUsername()));
        subscriptionOrderServiceModel.setSubscription(subscriptionTypeService.getSubscriptionByName(subscriptionOrderBindingModel.getSubscription()));


        String addressTo=subscriptionOrderBindingModel.getAddressTo();
        String addressFrom=subscriptionOrderServiceModel.getAddressFrom();

        ResponseEntity<DistanceDurationResponse> response= new RestTemplate().getForEntity("https://maps.googleapis.com/maps/api/distancematrix/json?origins="+addressFrom+"&destinations="+addressTo+"&mode=car&language=en&key="+API_KEY, DistanceDurationResponse.class);

        Float distance= Arrays.stream(Arrays.stream(response.getBody().getRows()).findFirst().get().getElements()).findFirst().get().getDistance().getValue();

        subscriptionOrderServiceModel.setDistance(distance/1000);
        SubscriptionOrderViewModel subscriptionOrderViewModel=subscriptionOrderService.createSubscriptionOrder(
                subscriptionOrderServiceModel
        );

        URI locationOfNewSubscriptionOrder =
              URI.create(String.format("/api/subscriptionOrders/%s", subscriptionOrderViewModel.getId()));
        return ResponseEntity.
                noContent().
                build();


    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AppErrors> onValidationFailure(MethodArgumentNotValidException exc) {
        AppErrors apiError = new AppErrors(HttpStatus.BAD_REQUEST);
        exc.getFieldErrors().forEach(fe ->
                apiError.addFieldWithError(fe.getField()));

        return ResponseEntity.badRequest().body(apiError);
    }
}

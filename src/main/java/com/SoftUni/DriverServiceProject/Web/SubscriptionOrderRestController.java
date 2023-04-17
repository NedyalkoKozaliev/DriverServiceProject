package com.SoftUni.DriverServiceProject.Web;

import com.SoftUni.DriverServiceProject.Models.DTO.SubscriptionOrderBindingModel;
import com.SoftUni.DriverServiceProject.Models.ServiceModels.SubscriptionOrderServiceModel;
import com.SoftUni.DriverServiceProject.Models.ViewModel.SubscriptionOrderViewModel;
import com.SoftUni.DriverServiceProject.Models.dataValidation.AppErorrs;
import com.SoftUni.DriverServiceProject.Service.SubscriptionOrderService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@CrossOrigin("*")
@RestController
public class SubscriptionOrderRestController {

    private final ModelMapper modelMapper;
    private final SubscriptionOrderService subscriptionOrderService;

    public SubscriptionOrderRestController(ModelMapper modelMapper, SubscriptionOrderService subscriptionOrderService) {
        this.modelMapper = modelMapper;
        this.subscriptionOrderService = subscriptionOrderService;
    }


    @RequestMapping(value = "/api/subscriptionOrders",
            produces = "application/json", method = {RequestMethod.POST})
    public ResponseEntity<SubscriptionOrderViewModel> MakeSubscriptionOrder(
            @AuthenticationPrincipal UserDetails principal,
            @Valid SubscriptionOrderBindingModel subscriptionOrderBindingModel
    ) {

        SubscriptionOrderServiceModel subscriptionOrderServiceModel=modelMapper.map(subscriptionOrderBindingModel,
                SubscriptionOrderServiceModel.class);
        SubscriptionOrderViewModel subscriptionOrderViewModel=subscriptionOrderService.createSubscriptionOrder(
                subscriptionOrderServiceModel
        );

        URI locationOfNewSubscriptionOrder =
              URI.create(String.format("/api/subscriptionOrders/%s", subscriptionOrderViewModel.getId()));
        return ResponseEntity.
                created(locationOfNewSubscriptionOrder).
                body(subscriptionOrderViewModel);


    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AppErorrs> onValidationFailure(MethodArgumentNotValidException exc) {
        AppErorrs appErorrs = new AppErorrs(HttpStatus.BAD_REQUEST);
        exc.getFieldErrors().forEach(fe ->
                appErorrs.addFieldWithError(fe.getField()));

        return ResponseEntity.badRequest().body(appErorrs);
    }
}

package com.SoftUni.DriverServiceProject.Web;

import com.SoftUni.DriverServiceProject.Models.DTO.SubscriptionTypeBindingModel;
import com.SoftUni.DriverServiceProject.Models.ServiceModels.SubscriptionTypeServiceModel;
import com.SoftUni.DriverServiceProject.Models.ViewModel.SubscriptionTypeViewModel;
import com.SoftUni.DriverServiceProject.Models.dataValidation.AppErrors;
import com.SoftUni.DriverServiceProject.Service.SubscriptionTypeService;
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
public class SubscriptionRestController {

    private final ModelMapper modelMapper;
    private final SubscriptionTypeService subscriptionTypeService;

    public SubscriptionRestController(ModelMapper modelMapper, SubscriptionTypeService subscriptionTypeService) {
        this.modelMapper = modelMapper;
        this.subscriptionTypeService = subscriptionTypeService;
    }

    @RequestMapping(value = "/api/subscriptions",
            produces = "application/json", method = {RequestMethod.POST})
    public ResponseEntity<SubscriptionTypeViewModel> neSubscriptionType(
            @AuthenticationPrincipal UserDetails principal, @RequestBody
    @Valid SubscriptionTypeBindingModel subscriptionTypeBindingModel
    ){

        SubscriptionTypeServiceModel subscriptionTypeServiceModel=modelMapper.map(subscriptionTypeBindingModel,
               SubscriptionTypeServiceModel.class );

        SubscriptionTypeViewModel subscriptionTypeViewModel=subscriptionTypeService.changeType(subscriptionTypeServiceModel);

        URI locationOfNewSubscription =
               URI.create(String.format("/api/subscriptions/%s", subscriptionTypeViewModel.getId()));


        return ResponseEntity.
                created(locationOfNewSubscription).
                body(subscriptionTypeViewModel);

    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AppErrors> onValidationFailure(MethodArgumentNotValidException exc) {
        AppErrors appErrors = new AppErrors(HttpStatus.BAD_REQUEST);
        exc.getFieldErrors().forEach(fe ->
                appErrors.addFieldWithError(fe.getField()));

        return ResponseEntity.badRequest().body(appErrors);
    }

}

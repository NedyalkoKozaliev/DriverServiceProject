package com.SoftUni.DriverServiceProject.Web;

import com.SoftUni.DriverServiceProject.Models.DTO.ChangeUserNameModel;
import com.SoftUni.DriverServiceProject.Models.ViewModel.UserViewModel;
import com.SoftUni.DriverServiceProject.Models.dataValidation.AppErrors;
import com.SoftUni.DriverServiceProject.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@CrossOrigin("*")
@RestController
public class UserRestController {

    private final UserService userService;
    private final SecurityContextRepository securityContextRepository;

    private final ModelMapper modelMapper;

    public UserRestController(UserService userService, SecurityContextRepository securityContextRepository, ModelMapper modelMapper) {
        this.userService = userService;
        this.securityContextRepository = securityContextRepository;
        this.modelMapper = modelMapper;
    }


    @RequestMapping(value="/api/users/{id}/email",
            produces="application/json",
            method = {RequestMethod.PUT})
    public ResponseEntity<UserViewModel> ChangeEmail(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails principal,
            @RequestBody @Valid ChangeUserNameModel changeUserNameModel, HttpServletRequest request,
            HttpServletResponse response){

            UserViewModel userViewModel=modelMapper.map(userService.findUserById(changeUserNameModel.getUserId()),UserViewModel.class);

        userService.changeUserName((changeUserNameModel), successfulAuth->{
            SecurityContextHolderStrategy strategy= SecurityContextHolder.getContextHolderStrategy();
            SecurityContext context=strategy.createEmptyContext();
            context.setAuthentication(successfulAuth);
            strategy.setContext(context);
            securityContextRepository.saveContext(context,request,response);});

    URI locationOfChanged=URI.create(String.format("/api/users/email/%s",changeUserNameModel.getUserId()));
        return ResponseEntity.
                created(locationOfChanged).body(userViewModel);


    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AppErrors> onValidationFailure(MethodArgumentNotValidException exc) {
        AppErrors appErrors = new AppErrors(HttpStatus.BAD_REQUEST);
        exc.getFieldErrors().forEach(fe ->
                appErrors.addFieldWithError(fe.getField()));

        return ResponseEntity.badRequest().body(appErrors);
    }
}

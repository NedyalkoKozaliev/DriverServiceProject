package com.SoftUni.DriverServiceProject.Web;

import com.SoftUni.DriverServiceProject.Models.DTO.ChangeUserNameModel;
import com.SoftUni.DriverServiceProject.Models.ViewModel.UserViewModel;
import com.SoftUni.DriverServiceProject.Models.dataValidation.AppErrors;
import com.SoftUni.DriverServiceProject.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
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

    public UserRestController(UserService userService, SecurityContextRepository securityContextRepository) {
        this.userService = userService;
        this.securityContextRepository = securityContextRepository;
    }


    @RequestMapping(value="/api/users/{id}/email",
            produces="application/json",
            method = {RequestMethod.PUT})
    public ResponseEntity<UserViewModel> ChangeEmail(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails principal,
            @RequestBody @Valid ChangeUserNameModel changeUserNameModel, HttpServletRequest request,
            HttpServletResponse response){



        userService.changeUserName((changeUserNameModel), successfulAuth->{
            SecurityContextHolderStrategy strategy= SecurityContextHolder.getContextHolderStrategy();
            SecurityContext context=strategy.createEmptyContext();
            context.setAuthentication(successfulAuth);
            strategy.setContext(context);
            securityContextRepository.saveContext(context,request,response);});


        return ResponseEntity.
                noContent().
                build();

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AppErrors> onValidationFailure(MethodArgumentNotValidException exc) {
        AppErrors appErrors = new AppErrors(HttpStatus.BAD_REQUEST);
        exc.getFieldErrors().forEach(fe ->
                appErrors.addFieldWithError(fe.getField()));

        return ResponseEntity.badRequest().body(appErrors);
    }
}

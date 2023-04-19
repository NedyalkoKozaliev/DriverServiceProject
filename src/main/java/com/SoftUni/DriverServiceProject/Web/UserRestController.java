package com.SoftUni.DriverServiceProject.Web;

import com.SoftUni.DriverServiceProject.Models.DTO.ChangeUserNameModel;
import com.SoftUni.DriverServiceProject.Models.ViewModel.UserViewModel;
import com.SoftUni.DriverServiceProject.Models.dataValidation.AppErorrs;
import com.SoftUni.DriverServiceProject.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value="/api/users/{id}/email",
            produces="application/json",
            method = {RequestMethod.PUT})
    public ResponseEntity<UserViewModel> ChangeEmail(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails principal,
            @RequestBody @Valid ChangeUserNameModel changeUserNameModel){

        userService.changeUserName(changeUserNameModel);

        return ResponseEntity.
                noContent().
                build();

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AppErorrs> onValidationFailure(MethodArgumentNotValidException exc) {
        AppErorrs appErorrs = new AppErorrs(HttpStatus.BAD_REQUEST);
        exc.getFieldErrors().forEach(fe ->
                appErorrs.addFieldWithError(fe.getField()));

        return ResponseEntity.badRequest().body(appErorrs);
    }
}

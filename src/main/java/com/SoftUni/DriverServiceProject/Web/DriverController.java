package com.SoftUni.DriverServiceProject.Web;

import com.SoftUni.DriverServiceProject.Models.DTO.OrderBindingModel;
import com.SoftUni.DriverServiceProject.Models.ViewModel.DriverViewModel;
import com.SoftUni.DriverServiceProject.Models.ViewModel.OrderViewModel;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/drivers")
public class DriverController {

    @PostMapping("/{id}" )
    public ResponseEntity<DriverViewModel> AssignOrder(
            @AuthenticationPrincipal UserDetails principal,
           @PathVariable Long id,
            @RequestBody @Valid OrderBindingModel orderBindingModel){
        return null;

    }


}

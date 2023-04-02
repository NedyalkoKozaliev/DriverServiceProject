package com.SoftUni.DriverServiceProject.Web;

import com.SoftUni.DriverServiceProject.Models.ViewModel.DriverViewModel;
import com.SoftUni.DriverServiceProject.Service.DriverService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/drivers")
public class DriverController {


    private final ModelMapper modelMapper;
    private final DriverService driverService;

    public DriverController(ModelMapper modelMapper, DriverService driverService) {
        this.modelMapper = modelMapper;
        this.driverService = driverService;
    }

//    @PutMapping("/api/drivers/{id}")
//    public ResponseEntity<OrderViewModel> AssignOrder(
//            @AuthenticationPrincipal UserDetails principal,
//            @PathVariable Long id,
//            @RequestBody @Valid OrderBindingModel orderBindingModel,
//            DriverRepository driverRepository, ModelMapper modelMapper, OrderService orderService){
//
//        OrderServiceModel orderServiceModel=modelMapper.map(orderBindingModel, OrderServiceModel.class);
//
//        OrderViewModel OrderView =
//                orderService.createOrder(orderServiceModel);
//        URI locationOfNewOrder =
//                URI.create(String.format("/api/drivers/%s/currentOrder",id));
//
//        Optional<Driver> driver=driverRepository.findById(id);
//
//        driver.ifPresent(driver1 ->{ driver1.setCurrentTask(orderService.getOrder(orderServiceModel.getId()));});
//           return ResponseEntity
//                   .created(locationOfNewOrder)
//                   .body(OrderView);}
//
//    @PutMapping("/api/drivers/{id}/currentOrder")
//    public ResponseEntity<OrderViewModel> FinishOrder(
//            @AuthenticationPrincipal UserDetails principal,
//            @PathVariable Long id,
//            @RequestBody @Valid OrderBindingModel orderBindingModel){
//        return null;
//
//    }

    @GetMapping("/{id}")
    public String driverDash(@PathVariable Long id, Model model){

        model
                .addAttribute("driver", modelMapper
                        .map(driverService.findDriverById(id), DriverViewModel.class));

        return "driverDash";
    }

}







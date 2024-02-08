package com.SoftUni.DriverServiceProject.Web;

import com.SoftUni.DriverServiceProject.Models.LogedIn.LoggedInDriver;
import com.SoftUni.DriverServiceProject.Models.ViewModel.DriverViewModel;
import com.SoftUni.DriverServiceProject.Models.ViewModel.OrderViewModel;
import com.SoftUni.DriverServiceProject.Models.ViewModel.SubscriptionOrderViewModel;
import com.SoftUni.DriverServiceProject.Service.DriverService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/drivers")
public class DriverController {


    private final ModelMapper modelMapper;
    private final DriverService driverService;

    public DriverController(ModelMapper modelMapper, DriverService driverService) {
        this.modelMapper = modelMapper;
        this.driverService = driverService;
    }



    @GetMapping("/{id}")
    public String driverDash(@PathVariable Long id, Model model,@AuthenticationPrincipal UserDetails principal  ){


        List<OrderViewModel>myOrders=driverService.findDriverById(id).getOrderTasks().stream().
                map(order -> modelMapper.map(order,OrderViewModel.class)).collect(Collectors.toList());
        List<SubscriptionOrderViewModel>mySubscriptions=driverService.findDriverById(id).getSubscriptionTasks().stream().
                map(subscriptionOrder -> modelMapper.map(subscriptionOrder,SubscriptionOrderViewModel.class)).collect(Collectors.toList());
        Float totalMileageOrders=myOrders.stream().map(OrderViewModel::getDistance).reduce((a,b)->a+b).orElse(null);
        BigDecimal TotalCost=myOrders.stream().map(OrderViewModel::getPrice).reduce(BigDecimal.ZERO,BigDecimal::add);
        Integer AmountOfOrders=myOrders.size();

        model
                .addAttribute("driver", modelMapper
                        .map(driverService.findDriverById(id), DriverViewModel.class));

        return "driverDash";
    }

}







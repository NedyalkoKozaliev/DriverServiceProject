package com.SoftUni.DriverServiceProject.Web;

import com.SoftUni.DriverServiceProject.Models.Entity.Order;
import com.SoftUni.DriverServiceProject.Models.ViewModel.ClientViewModel;
import com.SoftUni.DriverServiceProject.Models.ViewModel.SubscriptionOrderViewModel;
import com.SoftUni.DriverServiceProject.Service.ClientService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@Controller
@RequestMapping("/clients")
public class ClientsController {

    private final ClientService clientService;
    private final ModelMapper modelMapper;

    public ClientsController(ClientService clientService, ModelMapper modelMapper) {
        this.clientService = clientService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/{id}")
    public String ClientDash(@PathVariable Long id, Model model){

        List<Order> myOrders=clientService.findMyOrders(id);
        List<SubscriptionOrderViewModel> mySubscriptions=clientService.findMySubscriptions(id);
        Order myLastOrder=clientService.findLastOrder(id);
        BigDecimal billOrders= myOrders.stream().map(Order::getPrice).reduce(BigDecimal.ZERO,BigDecimal::add);
        BigDecimal billSubscriptions=mySubscriptions.stream().map(SubscriptionOrderViewModel::getPrice).reduce(BigDecimal.ZERO,BigDecimal::add);
        BigDecimal totalBill=billOrders.add(billSubscriptions);
        model
                .addAttribute("client", modelMapper
                        .map(clientService.findClientById(id), ClientViewModel.class));
        model.addAttribute("myorders", myOrders);
        model.addAttribute("mySubscriptions", mySubscriptions);
        model.addAttribute("myLastOrder", myLastOrder);
        model.addAttribute("billOrders", billOrders);
        model.addAttribute("billSubscriptions", billSubscriptions);
        model.addAttribute("total", totalBill);

        return "clientDash";

    }


    @GetMapping("/{id}/makeOrder")

    public String makeOrder(@PathVariable Long id, Model model) {

        model.addAttribute("client", clientService.findClientById(id));

        return "makeOrder";
    }
    @GetMapping("/{id}/makeSubscription")

    public String makeSubscription(@PathVariable Long id, Model model) {

        model.addAttribute("client", clientService.findClientById(id));

        return "makeSubscription";
    }

    }


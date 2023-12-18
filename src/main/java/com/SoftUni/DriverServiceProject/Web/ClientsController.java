package com.SoftUni.DriverServiceProject.Web;

import com.SoftUni.DriverServiceProject.Models.ViewModel.ClientViewModel;
import com.SoftUni.DriverServiceProject.Service.ClientService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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

        model
                .addAttribute("client", modelMapper
                        .map(clientService.findClientById(id), ClientViewModel.class));

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


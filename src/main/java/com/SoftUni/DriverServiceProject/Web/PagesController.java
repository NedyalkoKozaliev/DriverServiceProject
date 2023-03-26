package com.SoftUni.DriverServiceProject.Web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagesController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/pages/aboutus")
    public String aboutUs() {
        return "aboutus";
    }

    @GetMapping("/pages/contacts")
    public String contacts() {
        return "contacts";
    }

    @GetMapping("/users/admins")
    public String admins() {
        return "admins";
    }

    @GetMapping("/users/clients")
    public String clients() {
        return "clients";
    }
    @GetMapping("/users/clients/orders")
    public String orders() {
        return "order";
    }

    @GetMapping("/users/clients/subscriptions")
    public String subscriptions() {
        return "subscription";
    }

    @GetMapping("/drivers")
    public String drivers() {
        return "drivers";
    }
}



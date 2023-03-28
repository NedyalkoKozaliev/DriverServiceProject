package com.SoftUni.DriverServiceProject.Web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagesController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/aboutus")
    public String aboutUs() {
        return "aboutus";
    }

    @GetMapping("/contacts")
    public String contacts() {
        return "contacts";
    }

    @GetMapping("/admins")
    public String admins() {
        return "admins";
    }

    @GetMapping("/clients")
    public String clients() {
        return "clients";
    }
//    @GetMapping("/orders")
//    public String orders() {
//        return "order";
//    }

    @GetMapping("/subscriptions")
    public String subscriptions() {
        return "subscription";
    }

    @GetMapping("/drivers")
    public String drivers() {
        return "driver";
    }
}



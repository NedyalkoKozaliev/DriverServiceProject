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



//    @GetMapping("/orders")
//    public String createOrder() {
//        return "order";
//    }




//
//    @GetMapping("/drivers")
//    public String drivers() {
//        return "driver";
//    }
}



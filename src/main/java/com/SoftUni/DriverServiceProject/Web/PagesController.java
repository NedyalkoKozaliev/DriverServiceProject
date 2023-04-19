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

    @GetMapping("/youAreBusy")
    public String busy() {
        return "youAreBusy";
    }


}



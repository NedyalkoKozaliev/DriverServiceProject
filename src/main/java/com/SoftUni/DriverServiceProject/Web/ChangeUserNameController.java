package com.SoftUni.DriverServiceProject.Web;

import com.SoftUni.DriverServiceProject.Models.ViewModel.UserViewModel;
import com.SoftUni.DriverServiceProject.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class ChangeUserNameController {

    private final ModelMapper modelMapper;
    private final UserService userService;

    public ChangeUserNameController(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public String ClientDash(@PathVariable Long id, Model model){

        model
                .addAttribute("user", modelMapper
                        .map(userService.findUserById(id), UserViewModel.class));

        return "Change-UserName";

    }

}


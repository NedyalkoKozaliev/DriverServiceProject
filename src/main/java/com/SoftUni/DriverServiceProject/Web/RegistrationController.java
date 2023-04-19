package com.SoftUni.DriverServiceProject.Web;

import com.SoftUni.DriverServiceProject.Models.DTO.UserRegistrationDTO;
import com.SoftUni.DriverServiceProject.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private final UserService userService;
    private final SecurityContextRepository securityContextRepository;

    @Autowired
    public RegistrationController(UserService userService,
                                  SecurityContextRepository securityContextRepository) {
        this.userService = userService;

        this.securityContextRepository = securityContextRepository;
    }

    @GetMapping("/users/register")
    public String register() {
        return "auth-register";
    }

    @PostMapping("/users/register")
    public String registerNewUser(
            UserRegistrationDTO registrationDTO,
            HttpServletRequest request,
            HttpServletResponse response) {

        userService.registerUser(registrationDTO, successfulAuth -> {

            SecurityContextHolderStrategy strategy = SecurityContextHolder.getContextHolderStrategy();

            SecurityContext context = strategy.createEmptyContext();
            context.setAuthentication(successfulAuth);

            strategy.setContext(context);

            securityContextRepository.saveContext(context, request, response);
        });

        return "redirect:/";
    }
}

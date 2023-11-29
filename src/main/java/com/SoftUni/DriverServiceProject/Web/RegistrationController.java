package com.SoftUni.DriverServiceProject.Web;

import com.SoftUni.DriverServiceProject.Models.DTO.UserRegistrationDTO;
import com.SoftUni.DriverServiceProject.Models.ServiceModels.UserServiceModel;
import com.SoftUni.DriverServiceProject.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class RegistrationController {

    private final UserService userService;
     private final ModelMapper modelMapper;
    private final SecurityContextRepository securityContextRepository;

@Autowired
    public RegistrationController(UserService userService, ModelMapper modelMapper, SecurityContextRepository securityContextRepository) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    this.securityContextRepository = securityContextRepository;
}

    @GetMapping("/register")
    public String register(Model model) {
        if(!model.containsAttribute("userRegistrationDTO")){
            model.addAttribute("userRegistrationDTO",new UserRegistrationDTO());
        }

        return "auth-register";
    }

    @PostMapping("/register")
    public String registerNewUser(@Valid UserRegistrationDTO userRegistrationDTO,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userRegistrationDTO",userRegistrationDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationDTO",bindingResult);
            return "redirect:register";

        }

        userService.registerUser(modelMapper.
                map(userRegistrationDTO, UserServiceModel.class), successfulAuth->{
            SecurityContextHolderStrategy strategy= SecurityContextHolder.getContextHolderStrategy();
            SecurityContext context=strategy.createEmptyContext();
            context.setAuthentication(successfulAuth);
            strategy.setContext(context);
            securityContextRepository.saveContext(context,request,response);
        });

        return "redirect:/";
    }

    @ModelAttribute
    public UserRegistrationDTO userRegistrationDTO(){
        return new UserRegistrationDTO();
    }
}

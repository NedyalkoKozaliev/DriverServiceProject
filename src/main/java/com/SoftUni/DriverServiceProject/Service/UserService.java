package com.SoftUni.DriverServiceProject.Service;

import com.SoftUni.DriverServiceProject.Models.DTO.ChangeUserNameModel;
import com.SoftUni.DriverServiceProject.Models.DTO.UserRegistrationDTO;
import com.SoftUni.DriverServiceProject.Models.Entity.User;
import com.SoftUni.DriverServiceProject.Models.ServiceModels.UserServiceModel;
import org.springframework.security.core.Authentication;

import java.util.function.Consumer;

public interface UserService {


    void changeUserName(ChangeUserNameModel changeUserName, Consumer<Authentication> successfulLoginProcessor);

    void registerUser(UserServiceModel userServiceModel, Consumer<Authentication> successfulLoginProcessor);

    User findUserById(Long id);
}

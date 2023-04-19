package com.SoftUni.DriverServiceProject.Service;

import com.SoftUni.DriverServiceProject.Models.DTO.ChangeUserNameModel;
import com.SoftUni.DriverServiceProject.Models.DTO.UserRegistrationDTO;
import com.SoftUni.DriverServiceProject.Models.Entity.User;
import org.springframework.security.core.Authentication;

import java.util.function.Consumer;

public interface UserService {
    void changeUserName(ChangeUserNameModel changeUserName);

    void registerUser(UserRegistrationDTO registrationDTO,Consumer<Authentication> successfulLoginProcessor);

    User findUserById(Long id);
}

package com.SoftUni.DriverServiceProject.Service.Impl;

import com.SoftUni.DriverServiceProject.Models.DTO.ChangeUserNameModel;
import com.SoftUni.DriverServiceProject.Models.DTO.UserRegistrationDTO;
import com.SoftUni.DriverServiceProject.Models.Entity.User;
import com.SoftUni.DriverServiceProject.Repository.UserRepository;
import com.SoftUni.DriverServiceProject.Service.ApplicationUserDetailsService;
import com.SoftUni.DriverServiceProject.Service.UserService;
import com.SoftUni.DriverServiceProject.Service.exeptionHandling.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ApplicationUserDetailsService applicationUserDetailsService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ApplicationUserDetailsService applicationUserDetailsService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.applicationUserDetailsService = applicationUserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void changeUserName(ChangeUserNameModel changeUserName) {
        User user=userRepository.findById(changeUserName.getUserId()).orElseThrow(()->new ObjectNotFoundException("User with id " + changeUserName.getUserId() + " was not found!"));

        user.setEmail(changeUserName.getNewEmail());
        userRepository.save(user);

    }

    @Override
    public void registerUser(UserRegistrationDTO registrationDTO,Consumer<Authentication> successfulLoginProcessor) {
      User user = new User();
                user.setFirstName(registrationDTO.getFirstName());
                user.setLastName(registrationDTO.getLastName());
                user.setEmail(registrationDTO.getEmail());
                user.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));

        userRepository.save(user);

        UserDetails userDetails = applicationUserDetailsService.loadUserByUsername(registrationDTO.getEmail());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                userDetails.getPassword(),
                userDetails.getAuthorities()
        );

        successfulLoginProcessor.accept(authentication);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(()->new ObjectNotFoundException("User with id " + id + " was not found!"));
    }
}

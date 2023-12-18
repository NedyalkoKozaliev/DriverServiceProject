package com.SoftUni.DriverServiceProject.Service.Impl;

import com.SoftUni.DriverServiceProject.Models.DTO.ChangeUserNameModel;
import com.SoftUni.DriverServiceProject.Models.Entity.User;
import com.SoftUni.DriverServiceProject.Models.Enums.UserRoleEnum;
import com.SoftUni.DriverServiceProject.Models.ServiceModels.UserServiceModel;
import com.SoftUni.DriverServiceProject.Repository.UserRepository;
import com.SoftUni.DriverServiceProject.Repository.UserRoleRepository;
import com.SoftUni.DriverServiceProject.Service.ApplicationUserDetailsService;
import com.SoftUni.DriverServiceProject.Service.UserService;
import com.SoftUni.DriverServiceProject.Service.exeptionHandling.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ApplicationUserDetailsService applicationUserDetailsService;

    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ApplicationUserDetailsService applicationUserDetailsService, PasswordEncoder passwordEncoder, ModelMapper modelMapper,
                           UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.applicationUserDetailsService = applicationUserDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void changeUserName(ChangeUserNameModel changeUserName, Consumer<Authentication> successfulLoginProcessor) {
        User user=userRepository.findById(changeUserName.getUserId()).orElseThrow(()->new ObjectNotFoundException("User with id " + changeUserName.getUserId() + " was not found!"));

        user.setEmail(changeUserName.getNewEmail());
        userRepository.save(user);


        UserDetails userDetails = applicationUserDetailsService.loadUserByUsername(changeUserName.getNewEmail());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                userDetails.getPassword(),
                userDetails.getAuthorities()
        );
        successfulLoginProcessor.accept(authentication);

    }

    @Override
    public void registerUser(UserServiceModel userServiceModel,Consumer<Authentication> successfulLoginProcessor)
    {

        User user = modelMapper.map(userServiceModel, User.class);
        user.setPassword(passwordEncoder.encode(userServiceModel.getPassword()));
        user.setRoles(List.of(userRoleRepository.findUserRoleByRole(UserRoleEnum.Client)));


        userRepository.save(user);


       UserDetails userDetails = applicationUserDetailsService.loadUserByUsername(userServiceModel.getEmail());
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

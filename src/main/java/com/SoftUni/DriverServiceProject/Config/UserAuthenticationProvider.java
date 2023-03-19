package com.SoftUni.DriverServiceProject.Config;

import com.SoftUni.DriverServiceProject.Repository.DriverRepository;
import com.SoftUni.DriverServiceProject.Repository.UserRepository;
import com.SoftUni.DriverServiceProject.Service.ApplicationDriverDetailsService;
import com.SoftUni.DriverServiceProject.Service.ApplicationUserDetailsService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

//@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    private final UserRepository userRepository;


    public UserAuthenticationProvider(UserRepository userRepository, DriverRepository driverRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials()
                .toString();

            UserDetails user = new ApplicationUserDetailsService(userRepository).loadUserByUsername(username);


if(user!=null&& user.getPassword().equals(password)) {


//    if (user.getUsername().equals(username) &&
//            user.getPassword().equals(password)) {
        return new UsernamePasswordAuthenticationToken(
                username,
                password,
                user.getAuthorities());
    } else {
        throw new BadCredentialsException("Incorrect user credentials !!");


    }



    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}

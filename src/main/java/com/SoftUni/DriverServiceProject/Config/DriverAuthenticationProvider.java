package com.SoftUni.DriverServiceProject.Config;

import com.SoftUni.DriverServiceProject.Repository.DriverRepository;
import com.SoftUni.DriverServiceProject.Service.ApplicationDriverDetailsService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

//@Component
public class DriverAuthenticationProvider implements AuthenticationProvider{
    private final DriverRepository driverRepository;

    public DriverAuthenticationProvider(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

   // UserDetails driver = new ApplicationDriverDetailsService(driverRepository).loadUserByUsername(username);

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials()
                .toString();

       UserDetails driver = new ApplicationDriverDetailsService(driverRepository).loadUserByUsername(username);

        // UserDetailsService userDetailsServiceDriver=new ApplicationDriverDetailsService(driverRepository);

        if (driver != null && driver.getPassword().equals(password)) {
//        if (driver.getUsername().equals(username) &&
//        driver.getPassword().equals(password)) {
            return new UsernamePasswordAuthenticationToken(
                    username,
                    password,
                    driver.getAuthorities());
        } else {
            throw new BadCredentialsException("Incorrect user credentials !!");
        }
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}

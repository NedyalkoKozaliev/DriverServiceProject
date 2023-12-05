package com.SoftUni.DriverServiceProject.Config.AuthProviders;

import com.SoftUni.DriverServiceProject.Repository.DriverRepository;
import com.SoftUni.DriverServiceProject.Service.ApplicationDriverDetailsService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DriverAuthenticationProvider implements AuthenticationProvider {
    private final DriverRepository driverRepository;
    private final PasswordEncoder encoder;


    public DriverAuthenticationProvider(DriverRepository driverRepository, PasswordEncoder encoder) {
        this.driverRepository = driverRepository;

        this.encoder = encoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username= authentication.getPrincipal().toString();
       // String username = authentication.getName();
        String password = authentication.getCredentials()
                .toString();

        UserDetails driver = new ApplicationDriverDetailsService(driverRepository).loadUserByUsername(username);



//        if (driver == null){
//            throw new UsernameNotFoundException("Username not found!");
//        }
        if(!encoder.matches(password,driver.getPassword())||driver==null){
            throw new AuthenticationException("Invalid credentials!") {
            };
        }
        Authentication authenticated = new UsernamePasswordAuthenticationToken(
                driver, driver.getPassword(), driver.getAuthorities());
        return authenticated;

    }


    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}


package com.SoftUni.DriverServiceProject.Config.AuthProviders;

import com.SoftUni.DriverServiceProject.Repository.UserRepository;
import com.SoftUni.DriverServiceProject.Service.ApplicationUserDetailsService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    public UserAuthenticationProvider(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;


        this.encoder = encoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username=authentication.getPrincipal().toString();
        String password = authentication.getCredentials()
                .toString();

       UserDetails user = new ApplicationUserDetailsService(userRepository).loadUserByUsername(username);


//        if(user==null) {
//            throw new UsernameNotFoundException("Username not found!");

//        }
        if(!encoder.matches(password,user.getPassword())||user==null) {

            throw new AuthenticationException("Invalid credentials!"){};
        }

        Authentication authenticated = new UsernamePasswordAuthenticationToken(
                user, user.getPassword(), user.getAuthorities());
                return  authenticated;}


    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}

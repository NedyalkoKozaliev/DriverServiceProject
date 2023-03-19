package com.SoftUni.DriverServiceProject.Service;


import com.SoftUni.DriverServiceProject.Models.Entity.UserRole;
import com.SoftUni.DriverServiceProject.Repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    public ApplicationUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return
                userRepository.
                        findUserByEmail(username).
                        map(this::map).
                        orElseThrow(() -> new UsernameNotFoundException("User with name " + username + " not found!"));
    }

    private UserDetails map(com.SoftUni.DriverServiceProject.Models.Entity.User user) {
        return new User(
                user.getEmail(),
                user.getPassword(),
                extractAuthorities(user)
        );
    }

    private List<GrantedAuthority> extractAuthorities(com.SoftUni.DriverServiceProject.Models.Entity.User user) {
        return user.
                getRoles().
                stream().
                map(this::mapRole).
                toList();
    }

    private GrantedAuthority mapRole(UserRole userRole) {
        return new SimpleGrantedAuthority("ROLE_" + userRole.getRole().name());
    }
}

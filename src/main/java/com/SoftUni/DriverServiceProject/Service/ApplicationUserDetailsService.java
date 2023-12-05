package com.SoftUni.DriverServiceProject.Service;


import com.SoftUni.DriverServiceProject.Models.Entity.UserRole;
import com.SoftUni.DriverServiceProject.Models.LogedIn.CurrentUser;
import com.SoftUni.DriverServiceProject.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Autowired
    public ApplicationUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        com.SoftUni.DriverServiceProject.Models.Entity.User user=userRepository.findUserByEmail(username).orElseThrow(null);
//                      if(user==null) {
//                          throw new UsernameNotFoundException("User with name " + username + " not found!");
//                      }
                      return new CurrentUser(user);
    }

}

package com.SoftUni.DriverServiceProject.Service;

import com.SoftUni.DriverServiceProject.Models.Entity.Driver;
import com.SoftUni.DriverServiceProject.Models.Entity.DriverRole;
import com.SoftUni.DriverServiceProject.Models.Entity.UserRole;
import com.SoftUni.DriverServiceProject.Models.LogedIn.LoggedInDriver;
import com.SoftUni.DriverServiceProject.Repository.DriverRepository;
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
public class ApplicationDriverDetailsService implements UserDetailsService {

    private final DriverRepository driverRepository;

    @Autowired
    public ApplicationDriverDetailsService(DriverRepository driverRepository) {
        this.driverRepository=driverRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Driver driver=driverRepository.findDriverByEmail(username).orElseThrow(null);
//        if(driver==null){
//            throw new UsernameNotFoundException("User with name " + username + " not found!");
//        }
        return new LoggedInDriver(driver);

    }


}

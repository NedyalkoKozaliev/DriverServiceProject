package com.SoftUni.DriverServiceProject.Models.LogedIn;

import com.SoftUni.DriverServiceProject.Models.Entity.Driver;
import com.SoftUni.DriverServiceProject.Models.Entity.DriverRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class LoggedInDriver implements UserDetails {

    private Driver driver;

    public LoggedInDriver(Driver driver) {
        this.driver = driver;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> extractAuthorities= new ArrayList<>();

            extractAuthorities=driver.
                    getRoles().
                    stream().
                    map(this::mapRole).
                    toList();

            return extractAuthorities;


    }

     private GrantedAuthority mapRole(DriverRole driverRole) {
            return new SimpleGrantedAuthority("ROLE_" + driverRole.getRole().name());
        }

    @Override
    public String getPassword() {
        return driver.getPassword();
    }

    @Override
    public String getUsername() {
        return driver.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    public Long getId(){
        return this.driver.getId();
    }
}

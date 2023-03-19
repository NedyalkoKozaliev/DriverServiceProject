package com.SoftUni.DriverServiceProject;

import com.SoftUni.DriverServiceProject.Models.Entity.Driver;
import com.SoftUni.DriverServiceProject.Models.Entity.User;
import com.SoftUni.DriverServiceProject.Repository.DriverRepository;
import com.SoftUni.DriverServiceProject.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DriverServiceComLine implements CommandLineRunner {

    private static final String API_URL ="http://localhost:8080/users/login";

    private static final Logger LOGGER = LoggerFactory.getLogger(DriverServiceComLine.API_URL);

private final DriverRepository driverRepository;
private final UserRepository userRepository;

    public DriverServiceComLine(DriverRepository driverRepository, UserRepository userRepository) {
        this.driverRepository = driverRepository;
        this.userRepository = userRepository;
    }


    @Override
    public void run(String... args) throws Exception {

if(driverRepository.count()!=0) {
    List<String> drivers = driverRepository.findAll().stream().map(Driver::getEmail).toList();
    for (String meil : drivers) {
       System.out.printf("drivers in database:%s%n", meil);
    }
}else{
    System.out.println("empty driverRepo");
}
    if(userRepository.count()!=0){
        List<String> users=userRepository.findAll().stream().map(User::getEmail).toList();
        for(String meil: users){
            System.out.printf("users in database:%s%n",meil);
        }
    }else{
        System.out.println("empty userRepo");
    }

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentPrincipalName = authentication.getName();
//        LOGGER.info("logged user:{}",currentPrincipalName);

    }
}

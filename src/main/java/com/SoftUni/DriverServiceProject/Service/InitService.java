package com.SoftUni.DriverServiceProject.Service;

import com.SoftUni.DriverServiceProject.Models.Entity.*;
import com.SoftUni.DriverServiceProject.Models.Enums.UserRoleEnum;
import com.SoftUni.DriverServiceProject.Repository.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.SoftUni.DriverServiceProject.Models.Enums.CarTypeEnum.PassengerCar;

@Service
public class InitService {

    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final DriverRepository driverRepository;

    private final DriverRoleRepository driverRoleRepository;
    private final CarRepository carRepository;

    @Autowired
    public InitService(UserRoleRepository userRoleRepository,
                       UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       DriverRepository driverRepository, DriverRoleRepository driverRoleRepository,
                       CarRepository carRepository) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.driverRepository = driverRepository;
        this.driverRoleRepository = driverRoleRepository;
        this.carRepository = carRepository;
    }

    @PostConstruct
    public void init() {
        initRoles();
        initUsers();
    }

    private void initRoles() {
        if (userRoleRepository.count() == 0) {

            var adminRole = new UserRole().setRole(UserRoleEnum.Admin);
            var clientRole = new UserRole().setRole(UserRoleEnum.Client);



            userRoleRepository.save(adminRole);
            userRoleRepository.save(clientRole);

        }
        if(driverRoleRepository.count() ==0){
            var driverRole = new DriverRole().setRole(UserRoleEnum.Driver);

            driverRoleRepository.save(driverRole);

            
        }
    }

    private void initUsers() {
        if (userRepository.count() == 0) {
            initAdmin();
            initClient();
            initDriver();
        }
    }

    private void initAdmin(){
        var adminUser= new User();
        adminUser.setFirstName("Admin");
        adminUser.setLastName("Adminov");
        adminUser.setEmail("admin@example.com");
        adminUser.setPassword(passwordEncoder.encode("admin"));
        adminUser.setRoles(userRoleRepository.findAll());



     userRepository.save(adminUser);
    }

    private void initClient(){

        var clientRole = userRoleRepository.
                findUserRoleByRole(UserRoleEnum.Client).orElseThrow();

        var clientUser = new User();
                clientUser.setEmail("ivan@example.com");
                clientUser.setFirstName("Ivan");
                clientUser.setLastName("Ivanov");
                clientUser.setPassword(passwordEncoder.encode("ivan"));
                clientUser.setRoles(List.of(clientRole));

        userRepository.save(clientUser);
    }



    private void initDriver(){

        var driverRole = driverRoleRepository.
                findDriverRoleByRole(UserRoleEnum.Driver).orElseThrow();

        var driver = new Driver();
               driver.setEmail("stamat@example.com");
                driver.setFirstName("Stamat");
                driver.setLastName("Stamatov");
                driver.setPassword(passwordEncoder.encode("stamat"));
                driver.setRoles(List.of(driverRole));


        driverRepository.save(driver);
    }
}

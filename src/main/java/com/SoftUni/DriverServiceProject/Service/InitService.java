package com.SoftUni.DriverServiceProject.Service;

import com.SoftUni.DriverServiceProject.Models.Entity.*;
import com.SoftUni.DriverServiceProject.Models.Enums.SubscriptionEnumName;
import com.SoftUni.DriverServiceProject.Models.Enums.UserRoleEnum;
import com.SoftUni.DriverServiceProject.Repository.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
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
    private final PriceListRepository priceListRepository;

    private final SubscriptionRepository subscriptionRepository;

    @Autowired
    public InitService(UserRoleRepository userRoleRepository,
                       UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       DriverRepository driverRepository, DriverRoleRepository driverRoleRepository,
                       CarRepository carRepository, PriceListRepository priceListRepository, SubscriptionRepository subscriptionRepository) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.driverRepository = driverRepository;
        this.driverRoleRepository = driverRoleRepository;
        this.carRepository = carRepository;
        this.priceListRepository = priceListRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    @PostConstruct
    public void init() {
        initRoles();
        initUsers();
        initPriceList();
        initSubscriptions();
    }

    private void initSubscriptions() {
        if (subscriptionRepository.count() != 0) {
            return;
        }
        Arrays.stream(SubscriptionEnumName.values())
                .forEach((subscriptionEnumName -> {
                    Subscription subscription =new Subscription();
                    subscription.setName(subscriptionEnumName);
                    switch (subscriptionEnumName){
                        case ChildToKinderGarden -> {
                            subscription.setDescription("Everyday service to drive kids to Kindergarten and back.");
                            subscription.setPriceRate(0.7F);
                        }

                        case ChildToSchool -> {
                            subscription.setDescription("Drive students to school and back.");
                            subscription.setPriceRate(0.8F);
                        }
                        case toWork -> {
                            subscription.setDescription("Everyday service to drive you to work and back.");
                            subscription.setPriceRate(0.9F);
                        }
                    }
                   subscriptionRepository.save(subscription);
                }));
    }

    private void initPriceList() {
        if(priceListRepository.count()!=0){
            return;
        }
        var priceList=new PriceList();
        priceList.setName("BGNperKm");
        priceList.setPrice(BigDecimal.valueOf(2.00));

        priceListRepository.save(priceList);
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
                findUserRoleByRole(UserRoleEnum.Client);

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

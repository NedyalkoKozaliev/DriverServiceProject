package com.SoftUni.DriverServiceProject.Web;

import com.SoftUni.DriverServiceProject.Models.DTO.AssignSubscriptionBindingModel;
import com.SoftUni.DriverServiceProject.Models.DTO.OrderBindingModel;
import com.SoftUni.DriverServiceProject.Models.Entity.Driver;
import com.SoftUni.DriverServiceProject.Models.Entity.Order;
import com.SoftUni.DriverServiceProject.Models.Entity.Subscription;
import com.SoftUni.DriverServiceProject.Models.Entity.User;
import com.SoftUni.DriverServiceProject.Models.Enums.UserRoleEnum;
import com.SoftUni.DriverServiceProject.Models.ViewModel.OrderViewModel;
import com.SoftUni.DriverServiceProject.Models.ViewModel.SubscriptionOrderViewModel;
import com.SoftUni.DriverServiceProject.Repository.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.text.MatchesPattern;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static com.SoftUni.DriverServiceProject.Models.Enums.SubscriptionEnumName.toWork;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
//import static org.modelmapper.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WithMockUser("nek@test.com")
@SpringBootTest
@AutoConfigureMockMvc
public class DriverRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private DriverRoleRepository driverRoleRepository;

    private Driver testDriver;

    private User testUser;

    @BeforeEach
    void setUp() {
        testDriver = new Driver();
        testDriver.setId(6L);
        testDriver.setEmail("nek@test.com");
        testDriver.setFirstName("Nedyalko");
        testDriver.setLastName("Kozaliev");
        testDriver.setPassword("password");
        testDriver.setRoles(List.of(driverRoleRepository.findDriverRoleByRole(UserRoleEnum.Driver).orElse(null)));
        testDriver=driverRepository.save(testDriver);

        testUser = new User();
        testUser.setId(7L);
        testUser.setEmail("ivan@test.com");
        testUser.setFirstName("Ivan");
        testUser.setLastName("Kozaliev");
        testUser.setPassword("password");
        testUser.setRoles(List.of(userRoleRepository.findUserRoleByRole(UserRoleEnum.Admin)));
        testUser=userRepository.save(testUser);

    }
    @AfterEach
    void tearDown() {
        orderRepository.deleteAll();
        driverRepository.deleteAll();
    }

    @Test
    void testAssignOrder() throws Exception {
        OrderViewModel orderView=new OrderViewModel();
        orderView.setAddressFrom("Plovdiv");
        orderView.setAddressTo("Sofia");
        orderView.setNumberOfPassengers(3);
        orderView.setPrice(BigDecimal.valueOf(20.5));
        orderView.setDistance(555f);

        Order order1=new Order();
        order1.setAddressFrom("Plovdiv");
        order1.setAddressTo("Sofia");
        order1.setNumberOfPassengers(3);
        order1.setClient(testUser);
        order1.setPrice(BigDecimal.valueOf(20.5));
        //order1.setApproved(true);
        order1.setDistance(555f);
        orderRepository.save(order1);

        testDriver.setCurrentTask(order1);

        mockMvc.perform(
                        put("/api/drivers/6/currentOrder")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(orderView))
                                .accept(MediaType.APPLICATION_JSON)
                                .with(csrf())
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string("Location", MatchesPattern.matchesPattern("/api/drivers/6/currentOrder")))
                .andExpect(jsonPath("$.addressFrom").value(is("Plovdiv")))
                .andExpect(jsonPath("$.addressTo").value(is("Sofia")))
                .andExpect(jsonPath("$.numberOfPassengers").value(is(3)))
                .andExpect(jsonPath("$.price").value(is(BigDecimal.valueOf(20.5))))
                .andExpect(jsonPath("$.distance").value(is(555f)));
    }

    @Test
    void testFinishOrder() throws Exception {
        OrderViewModel orderView=new OrderViewModel();
        orderView.setAddressFrom("Plovdiv");
        orderView.setAddressTo("Sofia");
        orderView.setNumberOfPassengers(3);
        orderView.setPrice(BigDecimal.valueOf(20.5));
        orderView.setDistance(555f);

        Order order1=new Order();
        order1.setAddressFrom("plovdiv");
        order1.setAddressTo("sofia");
        order1.setNumberOfPassengers(4);
        order1.setClient(testUser);
        order1.setPrice(BigDecimal.valueOf(33));
        order1.setApproved(true);
        order1.setDistance(789123f);
        orderRepository.save(order1);
        testDriver.setCurrentTask(order1);

        mockMvc.perform(
                        put("/api/drivers/6/ordersList")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(orderView))
                                .accept(MediaType.APPLICATION_JSON)
                                .with(csrf())
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string("Location", MatchesPattern.matchesPattern("/api/drivers/6/ordersList")))
                .andExpect(status().isOk()).
                andExpect(jsonPath("$", hasSize(1))).
                andExpect(jsonPath("$.[0].addressFrom").value(is("Plovdiv")))
                .andExpect(jsonPath("$.[0].addressTo").value(is("Sofia")))
                .andExpect(jsonPath("$.[0].numberOfPassengers").value(is(3)))
                 .andExpect(jsonPath("$.[0]price").value(is(BigDecimal.valueOf(20.5))))
                .andExpect(jsonPath("$.[0]distance").value(is(555f)));

    }

    @Test
    void testassignTask() throws Exception {


         AssignSubscriptionBindingModel testBindingModel= new AssignSubscriptionBindingModel();
        testBindingModel.setSubscriptionId(1L);
        testBindingModel.setDriverId(6L);

        mockMvc.perform(
                        put("/api/drivers/6/SubscriptionTasks")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(testBindingModel))
                                .accept(MediaType.APPLICATION_JSON)
                                .with(csrf())
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string("Location", MatchesPattern.matchesPattern("/api/drivers/6/SubscriptionTasks")))
                .andExpect(status().isOk())
                 .andExpect(jsonPath("$.subscription.id").value(is(1)));


    }

    @Test
    void testgetCurrentTask() throws Exception {
       Order order1=new Order();
        order1.setAddressFrom("plovdiv");
        order1.setAddressTo("sofia");
        order1.setNumberOfPassengers(4);
        order1.setClient(testUser);
        order1.setPrice(BigDecimal.valueOf(33));
        order1.setApproved(true);
        order1.setDistance(789123f);
        orderRepository.save(order1);

        mockMvc.perform(get("/api/drivers/1/currentOrder")).
                andExpect(status().isOk()).
                andExpect(jsonPath("$.addressFrom", is("plovdiv"))).
                andExpect(jsonPath("$.addressTo", is("sofia"))).
                andExpect(jsonPath("$.numberOfPasengers", is(2))).
                 andExpect(jsonPath("$.client", is(testUser))).
                  andExpect(jsonPath("$.approved", is(true))).
                   andExpect(jsonPath("$.distance", is(789123)));
    }



}



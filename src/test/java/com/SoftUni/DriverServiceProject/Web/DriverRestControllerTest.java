package com.SoftUni.DriverServiceProject.Web;

import com.SoftUni.DriverServiceProject.Models.DTO.OrderBindingModel;
import com.SoftUni.DriverServiceProject.Models.Entity.Driver;
import com.SoftUni.DriverServiceProject.Models.Entity.Order;
import com.SoftUni.DriverServiceProject.Models.Entity.Subscription;
import com.SoftUni.DriverServiceProject.Models.Entity.User;
import com.SoftUni.DriverServiceProject.Models.ViewModel.OrderViewModel;
import com.SoftUni.DriverServiceProject.Models.ViewModel.SubscriptionOrderViewModel;
import com.SoftUni.DriverServiceProject.Repository.DriverRepository;
import com.SoftUni.DriverServiceProject.Repository.OrderRepository;
import com.SoftUni.DriverServiceProject.Repository.UserRepository;
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

    private Driver testDriver;

    private User testUser;

    @BeforeEach
    void setUp() {
        testDriver = new Driver();

        testDriver.setEmail("nek@test.com");
        testDriver.setFirstName("Nedyalko");
        testDriver.setLastName("Kozaliev");
        testDriver.setPassword("password");
        // testUser.setRoles(List.of(clientRole));
        testDriver=driverRepository.save(testDriver);
        testUser = new User();

        testUser.setEmail("ivan@test.com");
        testUser.setFirstName("Ivan");
        testUser.setLastName("Kozaliev");
        testUser.setPassword("password");
        // testUser.setRoles(List.of(clientRole));
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

        mockMvc.perform(
                        put("/api/drivers/1/currentOrder")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(orderView))
                                .accept(MediaType.APPLICATION_JSON)
                                .with(csrf())
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string("Location", MatchesPattern.matchesPattern("/api/drivers/1/currentOrder")))
                .andExpect(jsonPath("$.addressFrom").value(is("Plovdiv")))
                .andExpect(jsonPath("$.addressTo").value(is("Sofia")))
                .andExpect(jsonPath("$.numberOfPassengers").value(is(3)));
    }

    @Test
    void testFinishOrder() throws Exception {
        OrderViewModel orderView=new OrderViewModel();
        orderView.setAddressFrom("Plovdiv");
        orderView.setAddressTo("Sofia");
        orderView.setNumberOfPassengers(3);

        mockMvc.perform(
                        put("/api/drivers/1/ordersList")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(orderView))
                                .accept(MediaType.APPLICATION_JSON)
                                .with(csrf())
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string("Location", MatchesPattern.matchesPattern("/api/drivers/1/ordersList")))
                .andExpect(status().isOk()).
                andExpect(jsonPath("$", hasSize(1))).
                andExpect(jsonPath("$.[0].addressFrom").value(is("Plovdiv")))
                .andExpect(jsonPath("$.[0].addressTo").value(is("Sofia")))
                .andExpect(jsonPath("$.[0].numberOfPassengers").value(is(3)));

    }

    @Test
    void testassignTask() throws Exception {
        Subscription testSub= new Subscription();
        testSub.setDescription("some description");
        testSub.setPriceRate(1.0f);
        testSub.setName(toWork);

        SubscriptionOrderViewModel subscription=new SubscriptionOrderViewModel();
        subscription.setId(1L);
        subscription.setAddressFrom("Plovdiv");
        subscription.setAddressTo("Sofia");
        subscription.setSubscription(testSub);

        mockMvc.perform(
                        put("/api/drivers/1/SubscriptionTasks")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(subscription))
                                .accept(MediaType.APPLICATION_JSON)
                                .with(csrf())
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string("Location", MatchesPattern.matchesPattern("/api/drivers/1/SubscriptionTasks")))
                .andExpect(status().isOk()).
                andExpect(jsonPath("$", hasSize(1))).
                andExpect(jsonPath("$.[0].addressFrom").value(is("Plovdiv")))
                .andExpect(jsonPath("$.[0].addressTo").value(is("Sofia")))
                .andExpect(jsonPath("$.[0].subscription.name.name()").value(is("toWork")));


    }

    @Test
    void testgetCurrentTask() throws Exception {
        Order order1=initOrder("plovdiv","sofia",2);
        testDriver.setCurrentTask(order1);

        mockMvc.perform(get("/api/drivers/1/currentOrder")).
                andExpect(status().isOk()).
                andExpect(jsonPath("$.addressFrom", is("plovdiv"))).
                andExpect(jsonPath("$.addressTo", is("sofia"))).
                andExpect(jsonPath("$.numberOfPasengers", is(2)));
    }

    private Order initOrder(String from,String to,Integer num){
        Order order1=new Order();
        order1.setAddressFrom(from);
        order1.setAddressTo(to);
        order1.setNumberOfPassengers(num);
        order1.setClient(testUser);

        orderRepository.save(order1);

        return order1;
    }


}



package com.SoftUni.DriverServiceProject.Web;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import com.SoftUni.DriverServiceProject.Models.DTO.OrderBindingModel;
import com.SoftUni.DriverServiceProject.Models.Entity.Order;
import com.SoftUni.DriverServiceProject.Models.Entity.User;
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

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WithMockUser("nek@test.com")
@SpringBootTest
@AutoConfigureMockMvc
public class OrderRestControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private User testUser;


    @BeforeEach
    void setUp() {
        testUser = new User();

        testUser.setEmail("nek@test.com");
        testUser.setFirstName("Nedyalko");
        testUser.setLastName("Kozaliev");
        testUser.setPassword("password");
        // testUser.setRoles(List.of(clientRole));
        testUser=userRepository.save(testUser);
    }
    @AfterEach
    void tearDown() {
        orderRepository.deleteAll();
        userRepository.deleteAll();
    }


    @Test
    void testOrderIn() throws Exception {
        OrderBindingModel testOrder=new OrderBindingModel();
        testOrder.setAddressFrom("Plovdiv");
        testOrder.setAddressTo("Sofia");
        testOrder.setNumberOfPassengers(3);

        mockMvc.perform(
                        post("/api/orders")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(testOrder))
                                .accept(MediaType.APPLICATION_JSON)
                                .with(csrf())
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string("Location", MatchesPattern.matchesPattern("/api/orders/")))
                .andExpect(jsonPath("$.addressFrom").value(is("Plovdiv")))
            .andExpect(jsonPath("$.addressTo").value(is("Sofia")))
            .andExpect(jsonPath("$.numberOfPasengers").value(is(3)));


    }

    @Test
    void testgetOrders() throws Exception {
        Order order1=initOrder("plovdiv","sofia",2);
        Order order2=initOrder("Sofia","plovdiv",2);

        mockMvc.perform(get("/api/orders")).
                andExpect(status().isOk()).
                andExpect(jsonPath("$", hasSize(2))).
                andExpect(jsonPath("$.[0].addressFrom", is("plovdiv"))).
                andExpect(jsonPath("$.[1].addressFrom", is("sofia")));
    }



    @Test
    void testgetOrder() throws Exception {
        Order order1=initOrder("plovdiv","sofia",2);
        mockMvc.perform(get("/api/orders/1")).
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

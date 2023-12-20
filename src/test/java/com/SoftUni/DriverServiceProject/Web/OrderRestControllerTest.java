package com.SoftUni.DriverServiceProject.Web;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import com.SoftUni.DriverServiceProject.Models.DTO.OrderBindingModel;
import com.SoftUni.DriverServiceProject.Models.Entity.Order;
import com.SoftUni.DriverServiceProject.Models.Entity.User;
import com.SoftUni.DriverServiceProject.Models.Enums.UserRoleEnum;
import com.SoftUni.DriverServiceProject.Repository.OrderRepository;
import com.SoftUni.DriverServiceProject.Repository.UserRepository;
import com.SoftUni.DriverServiceProject.Repository.UserRoleRepository;
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
    @Autowired
    private UserRoleRepository userRoleRepository;

    private User testUser;


    @BeforeEach
    void setUp() {
        testUser = new User();

        testUser.setEmail("nek@test.com");
        testUser.setFirstName("Nedyalko");
        testUser.setLastName("Kozaliev");
        testUser.setPassword("password");
        testUser.setRoles(List.of(userRoleRepository.findUserRoleByRole(UserRoleEnum.Client)));
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
        testOrder.setAddressFrom("test1");
        testOrder.setAddressTo("test2");
        testOrder.setNumberOfPassengers(3);
         testOrder.setClient(5L);

        mockMvc.perform(
                        post("/api/orders")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(testOrder))
                                .accept(MediaType.APPLICATION_JSON)
                                .with(csrf())
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string("Location", MatchesPattern.matchesPattern("/api/orders/\\d+")))
                .andExpect(jsonPath("$.addressFrom").value(is("test1")))
            .andExpect(jsonPath("$.addressTo").value(is("test2")))
            .andExpect(jsonPath("$.numberOfPasengers").value(is(3)));


    }

    @Test
    void testgetOrders() throws Exception {
        //Order order1=initOrder("plovdiv","sofia",2);
        //Order order2=initOrder("Sofia","plovdiv",2);
        Order order1=new Order();
        order1.setId(1L);
        order1.setAddressFrom("test1");
        order1.setAddressTo("test2");
        order1.setNumberOfPassengers(3);
        order1.setPrice(BigDecimal.valueOf(456));
        order1.setClient(testUser);
        order1.setApproved(false);
        order1.setDistance(3456f);

        Order order2=new Order();
        order1.setAddressFrom("test3");
        order1.setAddressTo("test4");
        order1.setNumberOfPassengers(5);
        order1.setPrice(BigDecimal.valueOf(789));
        order1.setClient(testUser);
        order1.setApproved(true);
        order1.setDistance(3456f);

        mockMvc.perform(get("/api/orders")).
                andExpect(status().isOk()).
                andExpect(jsonPath("$", hasSize(1))).
                andExpect(jsonPath("$.[0].addressFrom", is("test3")));
               
    }



    @Test
    void testgetOrder() throws Exception {
         Order order1=new Order();
          order1.setId(1L);
        order1.setAddressFrom("test1");
        order1.setAddressTo("test2");
        order1.setNumberOfPassengers(3);
        order1.setPrice(BigDecimal.valueOf(456));
        order1.setClient(testUser);
        order1.setApproved(false);
        order1.setDistance(3456f);


        mockMvc.perform(get("/api/orders/1")).
                andExpect(status().isOk()).
                andExpect(jsonPath("$.addressFrom", is("test1"))).
                andExpect(jsonPath("$.addressTo", is("test2"))).
                andExpect(jsonPath("$.numberOfPasengers", is(2))).
                 andExpect(jsonPath("$.price", is(BigDecimal.valueOf(456)))).
                  andExpect(jsonPath("$.distance", is(3456f)));


    }

   

}

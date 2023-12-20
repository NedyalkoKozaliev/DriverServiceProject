package com.SoftUni.DriverServiceProject.Web;

import com.SoftUni.DriverServiceProject.Models.DTO.SubscriptionOrderBindingModel;
import com.SoftUni.DriverServiceProject.Models.Entity.User;
import com.SoftUni.DriverServiceProject.Models.Enums.SubscriptionEnumName;
import com.SoftUni.DriverServiceProject.Repository.SubscriptionOrderRepository;
import com.SoftUni.DriverServiceProject.Repository.SubscriptionRepository;
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
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WithMockUser("nek@test.com")
@SpringBootTest
@AutoConfigureMockMvc
public class SubscriptionOrderRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SubscriptionOrderRepository subscriptionOrderRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;;



    private User testUser;
    private User testUser1;


    @BeforeEach
    void setUp() {
        testUser = new User();

        testUser.setEmail("nek@test.com");
        testUser.setFirstName("Nedyalko");
        testUser.setLastName("Kozaliev");
        testUser.setPassword("password");
        // testUser.setRoles(List.of(clientRole)); to rework it
        testUser=userRepository.save(testUser);

        testUser1 = new User();

        testUser1.setEmail("Pep@test.com");
        testUser1.setFirstName("Petur");
        testUser1.setLastName("Petrov");
        testUser1.setPassword("password123");
        // testUser.setRoles(List.of(clientRole)); to rework it
        testUser1=userRepository.save(testUser1);
    }
    @AfterEach
    void tearDown() {
        subscriptionOrderRepository.deleteAll();
        subscriptionRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void testMakeSubscriptionOrder() throws Exception {
        SubscriptionOrderBindingModel testSubscriptionOrderBindingModel=new SubscriptionOrderBindingModel();
        testSubscriptionOrderBindingModel.setAddressFrom("Plovdiv");
        testSubscriptionOrderBindingModel.setAddressTo("Sofia");
        testSubscriptionOrderBindingModel.setSubscription(SubscriptionEnumName.valueOf("ChildToSchool"));


        mockMvc.perform(
                        post("/api/subscriptionOrders")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(testSubscriptionOrderBindingModel))
                                .accept(MediaType.APPLICATION_JSON)
                                .with(csrf())
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string("Location", MatchesPattern.matchesPattern("/api/subscriptionOrders/")))
                .andExpect(jsonPath("$.addressFrom").value(is("Plovdiv")))
                .andExpect(jsonPath("$.addressTo").value(is("Sofia")))
                .andExpect(jsonPath("$.subscription").value(is("ChildToSchool")))
                .andExpect(jsonPath("$.ClientId").value(is(testUser1.getId())));
    }

}

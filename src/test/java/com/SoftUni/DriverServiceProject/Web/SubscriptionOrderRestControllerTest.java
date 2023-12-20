package com.SoftUni.DriverServiceProject.Web;

import com.SoftUni.DriverServiceProject.Models.DTO.SubscriptionOrderBindingModel;
import com.SoftUni.DriverServiceProject.Models.Entity.User;
import com.SoftUni.DriverServiceProject.Models.Enums.SubscriptionEnumName;
import com.SoftUni.DriverServiceProject.Models.Enums.UserRoleEnum;
import com.SoftUni.DriverServiceProject.Repository.SubscriptionOrderRepository;
import com.SoftUni.DriverServiceProject.Repository.SubscriptionRepository;
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

import java.util.List;

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
    @Autowired
    private UserRoleRepository userRoleRepository;


    private User testUser;
    

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setId(1L);
        testUser.setEmail("nek@test.com");
        testUser.setFirstName("Nedyalko");
        testUser.setLastName("Kozaliev");
        testUser.setPassword("password");
        testUser.setRoles(List.of(userRoleRepository.findUserRoleByRole(UserRoleEnum.Client)));
        testUser=userRepository.save(testUser);

       
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
        testSubscriptionOrderBindingModel.setAddressFrom("test1");
        testSubscriptionOrderBindingModel.setAddressTo("test2");
        testSubscriptionOrderBindingModel.setSubscription(SubscriptionEnumName.valueOf("ChildToSchool"));
        testSubscriptionOrderBindingModel.setClientId(1L);

        mockMvc.perform(
                        post("/api/subscriptionOrders")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(testSubscriptionOrderBindingModel))
                                .accept(MediaType.APPLICATION_JSON)
                                .with(csrf())
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string("Location", MatchesPattern.matchesPattern("/api/subscriptionOrders/\\d+")))
                .andExpect(jsonPath("$.addressFrom").value(is("test1")))
                .andExpect(jsonPath("$.addressTo").value(is("test2")))
                .andExpect(jsonPath("$.subscription").value(is("ChildToSchool")));
                
    }

}

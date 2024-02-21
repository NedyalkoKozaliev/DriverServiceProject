package com.SoftUni.DriverServiceProject.Service;

import com.SoftUni.DriverServiceProject.Models.Entity.User;
import com.SoftUni.DriverServiceProject.Models.Entity.UserRole;
import com.SoftUni.DriverServiceProject.Models.Enums.UserRoleEnum;
import com.SoftUni.DriverServiceProject.Repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class ApplicationUserDetailsServiceTest {

    private User testUser;
    private UserRole adminRole, userRole;

    private ApplicationUserDetailsService userServiceTest;

    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void init() {

        //ARRANGE
        userServiceTest = new ApplicationUserDetailsService(mockUserRepository);

        adminRole = new UserRole();
        adminRole.setRole(UserRoleEnum.Admin);

        userRole = new UserRole();
        userRole.setRole(UserRoleEnum.Client);

        testUser = new User();
        testUser.setFirstName("Nedyalko");
        testUser.setEmail("nnn@nnn.com");
        testUser.setPassword("pass");
        testUser.setRoles(List.of(adminRole, userRole));
    }


    @Test
    void testUserFound() {

        // Arrange
        Mockito.when(mockUserRepository.findUserByEmail(testUser.getEmail())).
                thenReturn(Optional.of(testUser));

        // Act
        var actual = userServiceTest.loadUserByUsername(testUser.getEmail());

        String expectedRoles = "ROLE_Admin, ROLE_Client";
        String actualRoles = actual.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(
                Collectors.joining(", "));

        // Assert

        Assertions.assertEquals(actual.getUsername(), testUser.getEmail());
        Assertions.assertEquals(expectedRoles, actualRoles);
        Assertions.assertEquals(testUser.getPassword(),actual.getPassword());

    }



    @Test
    void testUserNotFound() {
        Assertions.assertThrows(
                NullPointerException.class,
                () -> userServiceTest.loadUserByUsername("non@not-exist.com")
        );
    }
}

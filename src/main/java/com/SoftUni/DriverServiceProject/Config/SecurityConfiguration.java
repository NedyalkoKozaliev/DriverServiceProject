package com.SoftUni.DriverServiceProject.Config;


import com.SoftUni.DriverServiceProject.Models.Entity.User;
import com.SoftUni.DriverServiceProject.Models.Enums.UserRoleEnum;
import com.SoftUni.DriverServiceProject.Repository.DriverRepository;
import com.SoftUni.DriverServiceProject.Repository.UserRepository;
import com.SoftUni.DriverServiceProject.Service.ApplicationDriverDetailsService;
import com.SoftUni.DriverServiceProject.Service.ApplicationUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

@Configuration
public class SecurityConfiguration {



//    @Autowired
//    DriverAuthenticationProvider driverAuthenticationProvider;
//    @Autowired
//    UserAuthenticationProvider userAuthenticationProvider;
//    @Autowired
    private final UserRepository userRepository;
    //@Autowired
    private final DriverRepository driverRepository;


       // @Autowired
    public SecurityConfiguration(UserRepository userRepository, DriverRepository driverRepository) {
        this.userRepository = userRepository;
        this.driverRepository = driverRepository;
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
      return new ApplicationUserDetailsService(userRepository);}

    @Bean
    public UserDetailsService driverDetailsService(DriverRepository driverRepository){
       return new ApplicationDriverDetailsService(driverRepository);
    }

//    @Bean
//    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
//        AuthenticationManagerBuilder authenticationManagerBuilder =
//                http.getSharedObject(AuthenticationManagerBuilder.class);
//        authenticationManagerBuilder.authenticationProvider(customAuthProvider);
//        authenticationManagerBuilder.inMemoryAuthentication()
//                .withUser("memuser")
//                .password(passwordEncoder().encode("pass"))
//                .roles("USER");
//        return authenticationManagerBuilder.build();
//    }
    @Bean
    public AuthenticationManager aut(HttpSecurity http) throws Exception{
        AuthenticationManagerBuilder authenticationManagerBuilder=
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService(userRepository));
       // authenticationManagerBuilder.authenticationProvider(userAuthenticationProvider);
        //authenticationManagerBuilder.authenticationProvider(driverAuthenticationProvider);
        authenticationManagerBuilder.userDetailsService(driverDetailsService(driverRepository));
        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,AuthenticationManager aut,SecurityContextRepository securityContextRepository) throws Exception {

        http.
                authorizeHttpRequests().
                requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll().
                requestMatchers("/", "/users/login", "/users/register", "/users/login-error","/aboutus","/contacts").permitAll().
                requestMatchers("/admins/**").hasRole(UserRoleEnum.Admin.toString()).
                requestMatchers("/clients/**").hasRole(UserRoleEnum.Client.toString()).
                requestMatchers("/drivers/**").hasRole(UserRoleEnum.Driver.toString()).
                anyRequest().authenticated().
                and().
                formLogin().
                loginPage("/users/login").
                usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY).
                passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY).
                defaultSuccessUrl("/").
                failureForwardUrl("/users/login-error").
                and().logout().
                logoutUrl("/users/logout").
                logoutSuccessUrl("/").
                invalidateHttpSession(true)
                .and().
                securityContext().
                securityContextRepository(securityContextRepository());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public ApplicationUserDetailsService applicationUserDetailsService(UserRepository userRepository){
//        return new ApplicationUserDetailsService(userRepository);
//    }
//    @Bean
//    public ApplicationDriverDetailsService applicationDriverDetailsService(DriverRepository driverRepository){
//        return new ApplicationDriverDetailsService(driverRepository);
//    }

    @Bean
    public SecurityContextRepository securityContextRepository() {
        return new DelegatingSecurityContextRepository(
                new RequestAttributeSecurityContextRepository(),
                new HttpSessionSecurityContextRepository()
        );
    }
    }




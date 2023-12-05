package com.SoftUni.DriverServiceProject.Config;


import com.SoftUni.DriverServiceProject.Config.AuthProviders.DriverAuthenticationProvider;
import com.SoftUni.DriverServiceProject.Config.AuthProviders.UserAuthenticationProvider;
import com.SoftUni.DriverServiceProject.Models.Enums.UserRoleEnum;
import com.SoftUni.DriverServiceProject.Repository.DriverRepository;
import com.SoftUni.DriverServiceProject.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {



   @Autowired
    private DriverAuthenticationProvider driverAuthenticationProvider;
    @Autowired
   private UserAuthenticationProvider userAuthenticationProvider;
////    @Autowired
    private final UserRepository userRepository;
    //@Autowired
    private final DriverRepository driverRepository;
    private final PasswordEncoder encoder;


       // @Autowired
    public SecurityConfiguration(UserRepository userRepository, DriverRepository driverRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.driverRepository = driverRepository;
        this.encoder = encoder;
    }


//    @Bean
//    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
//        AuthenticationManagerBuilder authenticationManagerBuilder =
//                http.getSharedObject(AuthenticationManagerBuilder.class);
//        authenticationManagerBuilder.authenticationProvider(userAuthenticationProvider);
//        authenticationManagerBuilder.authenticationProvider(driverAuthenticationProvider);
//        return authenticationManagerBuilder.build();
//    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) {
//        auth.authenticationProvider(userAuthenticationProvider)
//        .authenticationProvider(driverAuthenticationProvider);
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,SecurityContextRepository securityContextRepository) throws Exception {
      List<AuthenticationProvider> managers=new ArrayList<>();
       managers.add(driverAuthenticationProvider);
      managers.add(userAuthenticationProvider);
//        //ProviderManager manager=new ProviderManager(driverAuthenticationProvider,userAuthenticationProvider);
        ProviderManager manager=new ProviderManager(managers);

        http.
                authorizeHttpRequests().
                requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll().
                requestMatchers("/", "/users/login", "/users/register", "/users/login-error","/aboutus","/contacts").permitAll().
                requestMatchers("/admins/**").hasRole(UserRoleEnum.Admin.toString()).
                requestMatchers("/clients/**").hasRole(UserRoleEnum.Client.toString()).
                requestMatchers("/drivers/**").hasRole(UserRoleEnum.Driver.toString()).
                anyRequest().
                authenticated().


                and().
                formLogin().
                loginPage("/users/login").
                usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY).
                passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY).
                defaultSuccessUrl("/",true).
                failureForwardUrl("/users/login-error").
                and().logout().
                logoutUrl("/users/logout").
                logoutSuccessUrl("/").
                invalidateHttpSession(true)
                .and().
                securityContext().
                securityContextRepository(securityContextRepository())
                ;

        return http.authenticationManager(manager).build();
    }
//    @Bean
//    public SecurityFilterChain filterChainDriver(HttpSecurity http,SecurityContextRepository securityContextRepository) throws Exception {
//        ProviderManager manager=new ProviderManager(userAuthenticationProvider);
//        return http
//                .httpBasic(basic -> {})
//                .authorizeHttpRequests()
//                .anyRequest().authenticated().and()
//                .authenticationManager(manager)
//                .build();}



        @Bean
    public SecurityContextRepository securityContextRepository() {
        return new DelegatingSecurityContextRepository(
                new RequestAttributeSecurityContextRepository(),
                new HttpSessionSecurityContextRepository()
        );
    }
    }




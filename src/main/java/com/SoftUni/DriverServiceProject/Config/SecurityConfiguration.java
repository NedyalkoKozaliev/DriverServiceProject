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


    public UserDetailsService userDetailsService(UserRepository userRepository) {
      return new ApplicationUserDetailsService(userRepository);}

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
    public SecurityFilterChain filterChain(HttpSecurity http,AuthenticationManager aut) throws Exception {

        http.
                // defines which pages will be authorized
                        authorizeHttpRequests().
                // allow access to all static files (images, CSS, js)
                        requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll().
                // the URL-s below are available for all users - logged in and anonymous
                        requestMatchers("/", "/users/login", "/users/register", "/users/login-error","/aboutus","/contacts").permitAll().



                // only for admins
                        requestMatchers("/admins/**").hasRole(UserRoleEnum.Admin.toString()).


                requestMatchers("/clients/**").hasRole(UserRoleEnum.Client.toString()).

                requestMatchers("/drivers/**").hasRole(UserRoleEnum.Driver.toString()).
// to add visability about Logout (loged in users should not seening it and the register too)

        anyRequest().authenticated().
                and().
                // configure login with HTML form
                        formLogin().
                loginPage("/users/login").
                // the names of the user name, password input fields in the custom login form
                        usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY).
                passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY).
                // where do we go after login
                        defaultSuccessUrl("/").//use true argument if you always want to go there, otherwise go to previous page
                failureForwardUrl("/users/login-error").
                and().logout().//configure logout
                logoutUrl("/users/logout").
                logoutSuccessUrl("/").//go to homepage after logout
                invalidateHttpSession(true);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public UserDetailsService userDetailsService(UserRepository userRepository) {
//        return new AplicationUserDetailsService(userRepository);
    }




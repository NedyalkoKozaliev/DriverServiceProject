package com.SoftUni.DriverServiceProject.Config;

import com.SoftUni.DriverServiceProject.Service.DriverService;
import com.SoftUni.DriverServiceProject.Web.TakeOrderInterceptor;
import com.SoftUni.DriverServiceProject.Web.TimeCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfigurator implements WebMvcConfigurer {

    private final DriverService driverService;

    public InterceptorConfigurator(DriverService driverService) {
        this.driverService = driverService;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TakeOrderInterceptor(driverService))
               .addPathPatterns("/api/drivers/{id:\\d+}/currentOrder");
           
        registry.addInterceptor(new TimeCheckInterceptor())
                .addPathPatterns("/api/drivers/*");
    }

}

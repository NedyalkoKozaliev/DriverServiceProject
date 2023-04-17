package com.SoftUni.DriverServiceProject.Config;

import com.SoftUni.DriverServiceProject.Web.TakeOrderInterceptor;
import com.SoftUni.DriverServiceProject.Web.TimeCheckInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfigurator implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TakeOrderInterceptor())
                .addPathPatterns("/api/drivers/{id}/currentOrder");
        registry.addInterceptor(new TimeCheckInterceptor())
                .addPathPatterns("/api/drivers/*");
    }

}

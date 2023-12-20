package com.SoftUni.DriverServiceProject.Interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class TimeCheckInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory
            .getLogger(TimeCheckInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

        long startTime = (Long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        long TimeTaken = endTime - startTime;

        logger.info("Request URL::" + request.getRequestURL().toString()
                 			+ ":: End Time=" + System.currentTimeMillis());
                 	logger.info("Request URL::" + request.getRequestURL().toString()
                 			+ ":: Time Taken=" + (System.currentTimeMillis() - startTime));
    }
}
